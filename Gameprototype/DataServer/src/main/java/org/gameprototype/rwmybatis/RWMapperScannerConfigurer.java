package org.gameprototype.rwmybatis;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Hailei
 *  无奈于内部类,所以必须得照搬原来所有的东西,只修改了一下sqlsessiontemplate为rwsqlsessiontemplate
 */
public class RWMapperScannerConfigurer implements BeanFactoryPostProcessor, InitializingBean, ApplicationContextAware {

    private String basePackage;

    private boolean addToConfig = true;

    private SqlSessionFactory sqlSessionFactory;

    private RWSqlSessionTemplate sqlSessionTemplate;

    private Class<? extends Annotation> annotationClass;

    private Class<?> markerInterface;
    
    private ApplicationContext applicationContext;

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> superClass) {
        this.markerInterface = superClass;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void setSqlSessionTemplate(RWSqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * {@inheritDoc}
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.basePackage, "Property 'basePackage' is required");
    }

    /**
     * {@inheritDoc}
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        Scanner scanner = new Scanner((BeanDefinitionRegistry) beanFactory);
        scanner.setResourceLoader(this.applicationContext);

        scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
                ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    private final class Scanner extends ClassPathBeanDefinitionScanner {

        public Scanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        /**
         * Configures parent scanner to search for the right interfaces. It can search for all
         * interfaces or just for those that extends a markerInterface or/and those annotated with
         * the annotationClass
         */
        @Override
        protected void registerDefaultFilters() {
            boolean acceptAllInterfaces = true;

            // if specified, use the given annotation and / or marker interface
            if (RWMapperScannerConfigurer.this.annotationClass != null) {
                addIncludeFilter(new AnnotationTypeFilter(RWMapperScannerConfigurer.this.annotationClass));
                acceptAllInterfaces = false;
            }

            // override AssignableTypeFilter to ignore matches on the actual marker interface
            if (RWMapperScannerConfigurer.this.markerInterface != null) {
                addIncludeFilter(new AssignableTypeFilter(RWMapperScannerConfigurer.this.markerInterface) {
                    @Override
                    protected boolean matchClassName(String className) {
                        return false;
                    }
                });
                acceptAllInterfaces = false;
            }

            if (acceptAllInterfaces) {
                // default include filter that accepts all classes
                addIncludeFilter(new TypeFilter() {
                    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                            throws IOException {
                        return true;
                    }
                });
            }

            // always exclude interfaces with no methods
            addExcludeFilter(new TypeFilter() {
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                        throws IOException {
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    Class<?> candidateClass = null;

                    try {
                        candidateClass = getClass().getClassLoader().loadClass(classMetadata.getClassName());
                    } catch (ClassNotFoundException ex) {
                        return false;
                    }

                    if (candidateClass.getMethods().length == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }

        /**
         * Calls the parent search that will search and register all the candidates. Then the
         * registered objects are post processed to set them as MapperFactoryBeans
         */
        @Override
        protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

            if (beanDefinitions.isEmpty()) {
                logger.warn("No MyBatis mapper was found in '" + RWMapperScannerConfigurer.this.basePackage
                        + "' package. Please check your configuration.");
            } else {
                for (BeanDefinitionHolder holder : beanDefinitions) {
                    GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

                    if (logger.isDebugEnabled()) {
                        logger.debug("Creating MapperFactoryBean with name '" + holder.getBeanName() + "' and '"
                                + definition.getBeanClassName() + "' mapperInterface");
                    }

                    // the mapper interface is the original class of the bean
                    // but, the actual class of the bean is MapperFactoryBean
                    definition.getPropertyValues().add("mapperInterface", definition.getBeanClassName());
                    definition.setBeanClass(MapperFactoryBean.class);

                    definition.getPropertyValues().add("addToConfig", RWMapperScannerConfigurer.this.addToConfig);

                    if (RWMapperScannerConfigurer.this.sqlSessionFactory != null) {
                        definition.getPropertyValues().add("sqlSessionFactory",
                                RWMapperScannerConfigurer.this.sqlSessionFactory);
                    }

                    if (RWMapperScannerConfigurer.this.sqlSessionTemplate != null) {
                        definition.getPropertyValues().add("sqlSessionTemplate",
                                RWMapperScannerConfigurer.this.sqlSessionTemplate);
                    }
                }
            }

            return beanDefinitions;
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
        }

        @Override
        protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
            if (super.checkCandidate(beanName, beanDefinition)) {
                return true;
            } else {
                logger.warn("Skipping MapperFactoryBean with name '" + beanName + "' and '"
                        + beanDefinition.getBeanClassName() + "' mapperInterface"
                        + ". Bean already defined with the same name!");
                return false;
            }
        }
    }

}

