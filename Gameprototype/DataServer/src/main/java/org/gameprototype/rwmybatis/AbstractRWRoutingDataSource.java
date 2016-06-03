package org.gameprototype.rwmybatis;

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
 *
 */
public abstract class AbstractRWRoutingDataSource extends AbstractDataSource implements InitializingBean {
    
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
    
    private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
    

    public Connection getConnection() throws SQLException {
       
        return determineTargetDataSource().getConnection();
    }

    public Connection getConnection(String username, String password)
            throws SQLException {
        
        return determineTargetDataSource().getConnection(username,password);
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
    
}
