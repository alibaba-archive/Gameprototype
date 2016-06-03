package org.gameprototype.rwmybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.util.Assert;

/**
 * Thread safe, Spring managed, {@code SqlSession} that works with Spring
 * transaction management to ensure that that the actual SqlSession used is the
 * one associated with the current Spring transaction. In addition, it manages
 * the session life-cycle, including closing, committing or rolling back the
 * session as necessary based on the Spring transaction configuration.
 * <p>
 * The template needs a SqlSessionFactory to create SqlSessions, passed as a
 * constructor argument. It also can be constructed indicating the executor type
 * to be used, if not, the default executor type, defined in the session factory
 * will be used.
 * <p>
 * This template converts MyBatis PersistenceExceptions into unchecked
 * DataAccessExceptions, using, by default, a {@code MyBatisExceptionTranslator}.
 * <p>
 * Because SqlSessionTemplate is thread safe, a single instance can be shared
 * by all DAOs; there should also be a small memory savings by doing this. This
 * pattern can be used in Spring configuration files as follows:
 * 
 * <pre class="code">
 * {@code 
 * <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate"> 
 *   <constructor-arg ref="sqlSessionFactory" /> 
 * </bean>
 * }
 * </pre>
 * 
 * @see SqlSessionFactory
 * @see MyBatisExceptionTranslator
 * @version $Id: SqlSessionTemplate.java 3406 2010-12-20 17:58:31Z eduardo.macarron $
 */
public class RWSqlSessionTemplate implements SqlSession {

    private final SqlSessionFactory sqlSessionFactory;

    private final ExecutorType executorType;

    private final SqlSession sqlSessionProxy;

    private final PersistenceExceptionTranslator exceptionTranslator;

    /**
     * Constructs a Spring managed SqlSession with the {@code SqlSessionFactory} 
     * provided as an argument.
     *
     * @param sqlSessionFactory
     */
    public RWSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        this(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
    }

    /**
     * Constructs a Spring managed SqlSession with the {@code SqlSessionFactory} 
     * provided as an argument and the given {@code ExecutorType}
     * {@code ExecutorType} cannot be changed once the {@code SqlSessionTemplate}
     * is constructed.
     *
     * @param sqlSessionFactory
     * @param executorType
     */
    public RWSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
        this(sqlSessionFactory, executorType, 
                new MyBatisExceptionTranslator(
                        sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true));
    }

    /**
     * Constructs a Spring managed {@code SqlSession} with the given
     * {@code SqlSessionFactory} and {@code ExecutorType}.
     * A custom {@code SQLExceptionTranslator} can be provided as an 
     * argument so any {@code PersistenceException} thrown by MyBatis
     * can be custom translated to a {@code RuntimeException}
     * The {@code SQLExceptionTranslator} can also be null and thus no
     * exception translation will be done and MyBatis exceptions will be 
     * thrown
     *
     * @param sqlSessionFactory
     * @param executorType
     * @param exceptionTranslator
     */
    public RWSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType,
            PersistenceExceptionTranslator exceptionTranslator) {

        Assert.notNull(sqlSessionFactory, "Property 'sqlSessionFactory' is required");
        Assert.notNull(executorType, "Property 'executorType' is required");

        this.sqlSessionFactory = sqlSessionFactory;
        this.executorType = executorType;
        this.exceptionTranslator = exceptionTranslator;
        this.sqlSessionProxy = (SqlSession) Proxy.newProxyInstance(
                SqlSessionFactory.class.getClassLoader(),
                new Class[] { SqlSession.class }, 
                new SqlSessionInterceptor());
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return this.sqlSessionFactory;
    }

    public ExecutorType getExecutorType() {
        return this.executorType;
    }

    public PersistenceExceptionTranslator getPersistenceExceptionTranslator() {
        return this.exceptionTranslator;
    }

    /**
     * {@inheritDoc}
     */
    public Object selectOne(String statement) {
        return this.sqlSessionProxy.selectOne(statement);
    }

    /**
     * {@inheritDoc}
     */
    public Object selectOne(String statement, Object parameter) {
        return this.sqlSessionProxy.selectOne(statement, parameter);
    }

    /**
     * {@inheritDoc}
     */
    public Map<?, ?> selectMap(String statement, String mapKey) {
        return this.sqlSessionProxy.selectMap(statement, mapKey);
    }

    /**
     * {@inheritDoc}
     */
    public Map<?, ?> selectMap(String statement, Object parameter, String mapKey) {
        return this.sqlSessionProxy.selectMap(statement, parameter, mapKey);
    }

    /**
     * {@inheritDoc}
     */
    public Map<?, ?> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return this.sqlSessionProxy.selectMap(statement, parameter, mapKey, rowBounds);
    }

    /**
     * {@inheritDoc}
     */
    public List<?> selectList(String statement) {
        return this.sqlSessionProxy.selectList(statement);
    }

    /**
     * {@inheritDoc}
     */
    public List<?> selectList(String statement, Object parameter) {
        return this.sqlSessionProxy.selectList(statement, parameter);
    }

    /**
     * {@inheritDoc}
     */
    public List<?> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return this.sqlSessionProxy.selectList(statement, parameter, rowBounds);
    }

    /**
     * {@inheritDoc}
     */
    public void select(String statement, ResultHandler handler) {
        this.sqlSessionProxy.select(statement, handler);
    }

    /**
     * {@inheritDoc}
     */
    public void select(String statement, Object parameter, ResultHandler handler) {
        this.sqlSessionProxy.select(statement, parameter, handler);
    }

    /**
     * {@inheritDoc}
     */
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        this.sqlSessionProxy.select(statement, parameter, rowBounds, handler);
    }

    /**
     * {@inheritDoc}
     */
    public int insert(String statement) {
        return this.sqlSessionProxy.insert(statement);
    }

    /**
     * {@inheritDoc}
     */
    public int insert(String statement, Object parameter) {
        return this.sqlSessionProxy.insert(statement, parameter);
    }

    /**
     * {@inheritDoc}
     */
    public int update(String statement) {
        return this.sqlSessionProxy.update(statement);
    }

    /**
     * {@inheritDoc}
     */
    public int update(String statement, Object parameter) {
        return this.sqlSessionProxy.update(statement, parameter);
    }

    /**
     * {@inheritDoc}
     */
    public int delete(String statement) {
        return this.sqlSessionProxy.delete(statement);
    }

    /**
     * {@inheritDoc}
     */
    public int delete(String statement, Object parameter) {
        return this.sqlSessionProxy.delete(statement, parameter);
    }

    /**
     * {@inheritDoc}
     */
    public <T> T getMapper(Class<T> type) {
        return getConfiguration().getMapper(type, this);
    }

    /**
     * {@inheritDoc}
     */
    public void commit() {
        throw new UnsupportedOperationException("Manual commit is not allowed over a Spring managed SqlSession");
    }

    /**
     * {@inheritDoc}
     */
    public void commit(boolean force) {
        throw new UnsupportedOperationException("Manual commit is not allowed over a Spring managed SqlSession");
    }

    /**
     * {@inheritDoc}
     */
    public void rollback() {
        throw new UnsupportedOperationException("Manual rollback is not allowed over a Spring managed SqlSession");
    }

    /**
     * {@inheritDoc}
     */
    public void rollback(boolean force) {
        throw new UnsupportedOperationException("Manual rollback is not allowed over a Spring managed SqlSession");
    }

    /**
     * {@inheritDoc}
     */
    public void close() {
        throw new UnsupportedOperationException("Manual close is not allowed over a Spring managed SqlSession");
    }

    /**
     * {@inheritDoc}
     */
    public void clearCache() {
        this.sqlSessionProxy.clearCache();
    }

    /**
     * {@inheritDoc}
     */
    public Configuration getConfiguration() {
        return this.sqlSessionFactory.getConfiguration();
    }

    /**
     * {@inheritDoc}
     */
    public Connection getConnection() {
        return this.sqlSessionProxy.getConnection();
    }

    /**
     * Proxy needed to route MyBatis method calls to the proper SqlSession got
     * from String's Transaction Manager
     * It also unwraps exceptions thrown by {@code Method#invoke(Object, Object...)} to
     * pass a {@code PersistenceException} to the {@code PersistenceExceptionTranslator}.
     */
    private class SqlSessionInterceptor implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //第一個參數为statementId,
            //Assume statement must be not null.Don't check in here
            String statementId = (String)args[0];
            MappedStatement statement = RWSqlSessionTemplate.this.sqlSessionFactory.getConfiguration().getMappedStatement(statementId);
            if(statement.getSqlCommandType() == SqlCommandType.SELECT){
                RoutingDataSource.setKey("read");
            }else{
                RoutingDataSource.setKey("write");
            }
            
            final SqlSession sqlSession = SqlSessionUtils.getSqlSession(
                    RWSqlSessionTemplate.this.sqlSessionFactory,
                    RWSqlSessionTemplate.this.executorType,
                    RWSqlSessionTemplate.this.exceptionTranslator);
            try {
                Object result = method.invoke(sqlSession, args);
                if (!SqlSessionUtils.isSqlSessionTransactional(sqlSession, RWSqlSessionTemplate.this.sqlSessionFactory)) {
                    sqlSession.commit();
                }
                
                return result;
            } catch (Throwable t) {
                Throwable unwrapped = ExceptionUtil.unwrapThrowable(t);
                if (RWSqlSessionTemplate.this.exceptionTranslator != null && unwrapped instanceof PersistenceException) {
                    unwrapped = RWSqlSessionTemplate.this.exceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
                }
                throw unwrapped;
            } finally {
              //因为是针对每一个sql,选择不同的数据源,执行结束要清除
                RoutingDataSource.clear();
                SqlSessionUtils.closeSqlSession(sqlSession, RWSqlSessionTemplate.this.sqlSessionFactory);
            }
        }
    }

	@Override
	public List<BatchResult> flushStatements() {
		// TODO Auto-generated method stub
		return null;
	}

}
