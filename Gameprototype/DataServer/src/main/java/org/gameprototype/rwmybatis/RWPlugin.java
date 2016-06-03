package org.gameprototype.rwmybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;



/**
 * 不侵入mybatis的逻辑，实现读写分离
 * @author Hailei
 *
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class RWPlugin implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        
       
        Connection conn = (Connection)invocation.getArgs()[0];
        //如果是采用了我们代理,则路由数据源
        if(conn instanceof ConnectionProxy){
            StatementHandler statementHandler = (StatementHandler) invocation
                    .getTarget();
            
            MappedStatement mappedStatement = null;
            if (statementHandler instanceof RoutingStatementHandler) {
                StatementHandler delegate = (StatementHandler) ReflectionUtils
                        .getFieldValue(statementHandler, "delegate");
                mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(
                        delegate, "mappedStatement");
            } else {
                mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(
                        statementHandler, "mappedStatement");
            }
            String key = AbstractRWRoutingDataSourceProxy.WRITE;
            
            if(mappedStatement.getSqlCommandType() == SqlCommandType.SELECT){
                key = AbstractRWRoutingDataSourceProxy.READ;
            }else{
                key = AbstractRWRoutingDataSourceProxy.WRITE;
            }
            
            ConnectionProxy conToUse = (ConnectionProxy)conn;
            conToUse.getTargetConnection(key);
            
        }
        
        return invocation.proceed();
        
    }

    public Object plugin(Object target) {
        
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        //NOOP
        
    }

}
