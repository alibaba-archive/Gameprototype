package org.gameprototype.rwmybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.util.Assert;

/**
 * @author Hailei
 * 读写分离的数据源，可配置一个write和多个read-only的数据源，并提供对read-only数据源做负载均衡的接口
 * 借鉴了LazyConnectionDataSourceProxy的connection Proxy实现方式
 * 没有去检查所有注册的数据源的conneciton的isolation和commit的默认属性。如有需要，在初始化的过程中检测
 * 该类仍然为一个抽象类，需要实现load balance的策略
 *
 *
 */
public abstract class AbstractRWRoutingDataSourceProxy extends AbstractDataSource implements InitializingBean {
    
    //配置文件中配置的read-only datasoure
    //可以为真实的datasource，也可以jndi的那种
    private List<Object>  readDataSoures;
    private List<DataSource> resolvedReadDataSources;
    
    public static String READ;
    public static String WRITE;
    
    private   String readKey;
    private   String writeKey;
    
    private Object  writeDataSource;
    private DataSource resolvedWriteDataSource;
    //read-only data source的数量,做负载均衡的时候需要
    private int readDsSize;
    
    private boolean defaultAutoCommit = true;
    private int defaultTransactionIsolation = Connection.TRANSACTION_READ_COMMITTED;
    
    private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
    

    public Connection getConnection() throws SQLException {
       
        return (Connection) Proxy.newProxyInstance(
                ConnectionProxy.class.getClassLoader(),
                new Class[] {ConnectionProxy.class},
                new RWConnectionInvocationHandler());
    }

    public Connection getConnection(String username, String password)
            throws SQLException {
        
       return (Connection) Proxy.newProxyInstance(
                ConnectionProxy.class.getClassLoader(),
                new Class[] {ConnectionProxy.class},
                new RWConnectionInvocationHandler(username,password));
    }
    
    public int getReadDsSize(){
        return readDsSize;
    }
    
    public List<DataSource> getResolvedReadDataSources() {
        return resolvedReadDataSources;
    }

    public void afterPropertiesSet() throws Exception {
        
        if(writeDataSource == null){
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        this.resolvedWriteDataSource = resolveSpecifiedDataSource(writeDataSource);
        
        resolvedReadDataSources = new ArrayList<DataSource>(readDataSoures.size());
        for(Object item : readDataSoures){
            resolvedReadDataSources.add(resolveSpecifiedDataSource(item));
        }
        readDsSize = readDataSoures.size();
        READ = readKey;
        WRITE = writeKey;
        
    }
    
    public void setReadDataSoures(List<Object> readDataSoures) {
        this.readDataSoures = readDataSoures;
    }

    public void setReadKey(String readKey) {
        this.readKey = readKey;
    }

    public void setWriteKey(String writeKey) {
        this.writeKey = writeKey;
    }

    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    protected DataSource determineTargetDataSource() {
        Assert.notNull(this.resolvedReadDataSources, "DataSource router not initialized");
        String lookupKey = determineCurrentLookupKey();
        if(WRITE.equals(lookupKey)){
            return resolvedWriteDataSource;
        }else{
            return loadBalance();
        }
    }
    
    protected DataSource determineTargetDataSource(String key) {
        Assert.notNull(this.resolvedReadDataSources, "DataSource router not initialized");
       // String lookupKey = determineCurrentLookupKey();
        if(WRITE.equals(key)){
            return resolvedWriteDataSource;
        }else{
            return loadBalance();
        }
    }

//    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//        // NOOP Just ignore
//        return null;
//    }
    
    /**
     * 获取真实的data source
     * @param dataSource (jndi | real data source)
     * @return
     * @throws IllegalArgumentException
     */
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        if (dataSource instanceof DataSource) {
            return (DataSource) dataSource;
        }
        else if (dataSource instanceof String) {
            return this.dataSourceLookup.getDataSource((String) dataSource);
        }
        else {
            throw new IllegalArgumentException(
                    "Illegal data source value - only [javax.sql.DataSource] and String supported: " + dataSource);
        }
    }

    protected abstract String determineCurrentLookupKey();
    
    protected abstract DataSource loadBalance();
    
    /**
     * Invocation handler that defers fetching an actual JDBC Connection
     * until first creation of a Statement.
     */
    private class RWConnectionInvocationHandler implements InvocationHandler {

        private String username;

        private String password;

        private Boolean readOnly = Boolean.FALSE;

        private Integer transactionIsolation;

        private Boolean autoCommit;

        private boolean closed = false;

        private Connection target;

        public RWConnectionInvocationHandler() {
            
        }

        public RWConnectionInvocationHandler(String username, String password) {
            this();
            this.username = username;
            this.password = password;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Invocation on ConnectionProxy interface coming in...

            if (method.getName().equals("equals")) {
                // We must avoid fetching a target Connection for "equals".
                // Only consider equal when proxies are identical.
                return (proxy == args[0] ? Boolean.TRUE : Boolean.FALSE);
            }
            else if (method.getName().equals("hashCode")) {
                // We must avoid fetching a target Connection for "hashCode",
                // and we must return the same hash code even when the target
                // Connection has been fetched: use hashCode of Connection proxy.
                return new Integer(System.identityHashCode(proxy));
            }
            else if (method.getName().equals("getTargetConnection")) {
                // Handle getTargetConnection method: return underlying connection.
                return getTargetConnection(method,args);
            }

            if (!hasTargetConnection()) {
                // No physical target Connection kept yet ->
                // resolve transaction demarcation methods without fetching
                // a physical JDBC Connection until absolutely necessary.

                if (method.getName().equals("toString")) {
                    return "RW Routing DataSource Proxy";
                }
                else if (method.getName().equals("isReadOnly")) {
                    return this.readOnly;
                }
                else if (method.getName().equals("setReadOnly")) {
                    this.readOnly = (Boolean) args[0];
                    return null;
                }
                else if (method.getName().equals("getTransactionIsolation")) {
                    if (this.transactionIsolation != null) {
                        return this.transactionIsolation;
                    }
                    return defaultTransactionIsolation;
                    // Else fetch actual Connection and check there,
                    // because we didn't have a default specified.
                }
                else if (method.getName().equals("setTransactionIsolation")) {
                    this.transactionIsolation = (Integer) args[0];
                    return null;
                }
                else if (method.getName().equals("getAutoCommit")) {
                    if (this.autoCommit != null) 
                        return this.autoCommit;
                    return defaultAutoCommit;
                    // Else fetch actual Connection and check there,
                    // because we didn't have a default specified.
                }
                else if (method.getName().equals("setAutoCommit")) {
                    this.autoCommit = (Boolean) args[0];
                    return null;
                }
                else if (method.getName().equals("commit")) {
                    // Ignore: no statements created yet.
                    return null;
                }
                else if (method.getName().equals("rollback")) {
                    // Ignore: no statements created yet.
                    return null;
                }
                else if (method.getName().equals("getWarnings")) {
                    return null;
                }
                else if (method.getName().equals("clearWarnings")) {
                    return null;
                }
                else if (method.getName().equals("isClosed")) {
                    return (this.closed ? Boolean.TRUE : Boolean.FALSE);
                }
                else if (method.getName().equals("close")) {
                    // Ignore: no target connection yet.
                    this.closed = true;
                    return null;
                }
                else if (this.closed) {
                    // Connection proxy closed, without ever having fetched a
                    // physical JDBC Connection: throw corresponding SQLException.
                    throw new SQLException("Illegal operation: connection is closed");
                }
            }

            // Target Connection already fetched,
            // or target Connection necessary for current operation ->
            // invoke method on target connection.
            try {
                return method.invoke(target, args);
            }
            catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }

        /**
         * Return whether the proxy currently holds a target Connection.
         */
        private boolean hasTargetConnection() {
            return (this.target != null);
        }

        /**
         * Return the target Connection, fetching it and initializing it if necessary.
         */
        private Connection getTargetConnection(Method operation,Object[] args) throws SQLException {
            
            if (this.target == null) {
                String key = (String) args[0];
                // No target Connection held -> fetch one.
                if (logger.isDebugEnabled()) {
                    logger.debug("Connecting to database for operation '" + operation.getName() + "'");
                }

                // Fetch physical Connection from DataSource.
                this.target = (this.username != null) ?
                        determineTargetDataSource(key).getConnection(this.username, this.password) :
                        determineTargetDataSource(key).getConnection();

                // If we still lack default connection properties, check them now.
                //checkDefaultConnectionProperties(this.target);

                // Apply kept transaction settings, if any.
                if (this.readOnly.booleanValue()) {
                    this.target.setReadOnly(this.readOnly.booleanValue());
                }
                if (this.transactionIsolation != null) {
                    this.target.setTransactionIsolation(this.transactionIsolation.intValue());
                }
                if (this.autoCommit != null && this.autoCommit.booleanValue() != this.target.getAutoCommit()) {
                    this.target.setAutoCommit(this.autoCommit.booleanValue());
                }
            }

            else {
                // Target Connection already held -> return it.
                if (logger.isDebugEnabled()) {
                    logger.debug("Using existing database connection for operation '" + operation.getName() + "'");
                }
            }

            return this.target;
        }
    }
    
}
