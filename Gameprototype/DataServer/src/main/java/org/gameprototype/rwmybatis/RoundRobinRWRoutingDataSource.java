package org.gameprototype.rwmybatis;

import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.springframework.core.NamedThreadLocal;

/**
 * 采用简单的round robin的负载均衡策略的读写分离
 * @author Hailei
 *
 */
public class RoundRobinRWRoutingDataSource extends AbstractRWRoutingDataSource {

    private final static NamedThreadLocal<String> keys = new NamedThreadLocal<String>(
            "routingdatasource's key");
    
    private AtomicInteger count = new AtomicInteger(0) ;

    public static void setKey(String key) {

        keys.set(key);
    }

    public static void clear() {
        keys.remove();
    }

    @Override
    protected String determineCurrentLookupKey() {
        return keys.get();
    }

    @Override
    protected DataSource loadBalance() {
        int index = Math.abs(count.incrementAndGet())% getReadDsSize();
        return getResolvedReadDataSources().get(index);
    }

}
