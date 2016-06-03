package org.gameprototype.rwmybatis;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class MapperFactoryBean<T> extends SqlSessionDaoSupport implements FactoryBean<T> {

    private Class<T> mapperInterface;

    private boolean addToConfig = true;

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void checkDaoConfig() {
        super.checkDaoConfig();
        
        Assert.notNull(this.mapperInterface, "Property 'mapperInterface' is required");

        Configuration configuration = getSqlSession().getConfiguration();
        if (this.addToConfig && !configuration.hasMapper(this.mapperInterface)) {
            try {
                configuration.addMapper(this.mapperInterface);
            } catch (Throwable t) {
                logger.error("Error while adding the mapper '" + this.mapperInterface + "' to configuration.", t);
                throw new IllegalArgumentException(t);
            } finally {
                ErrorContext.instance().reset();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public T getObject() throws Exception {
        return getSqlSession().getMapper(this.mapperInterface);
    }

    /**
     * {@inheritDoc}
     */
    public Class<T> getObjectType() {
        return this.mapperInterface;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSingleton() {
        return true;
    }

}
