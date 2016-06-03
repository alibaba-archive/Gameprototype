package org.gameprototype.rwmybatis;

import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.springframework.core.NamedThreadLocal;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Hailei
 *   DateSource不是线程安全,所以我们用threadlocal来保证当前线程要使用的数据源
 *   2012-04-18 加入round robin的负载均衡策略
 *   首先得有这样一个datasource的key命名约定
 *   write
 *   read0
 *   read1
 *   read2
 *   以此类推
 */
public class RoutingDataSource  extends AbstractRoutingDataSource{
    
    private AtomicInteger  count = new AtomicInteger(0);

    
    private final static NamedThreadLocal<String> keys = new NamedThreadLocal<String>("routingdatasource's key"); 
//    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//        //NooP
//        return null;
//    }
    
    public static void setKey(String key){
        
           keys.set(key);
    }

    public static void clear(){
        keys.remove();
    }
    

    @Override
    protected Object determineCurrentLookupKey() {
        return keys.get();
    }

}
