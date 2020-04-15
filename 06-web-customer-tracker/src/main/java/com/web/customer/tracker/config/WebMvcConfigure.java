package com.web.customer.tracker.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = "com.web.customer.tracker")
@PropertySource({"classpath:persistence-mysql.properties",
        "classpath:security-persistence-mysql.properties"})
public class WebMvcConfigure implements WebMvcConfigurer {

    public static final String RESOLVER_PREFIX = "/WEB-INF/view/";
    public static final String RESOLVER_SUFFIX = ".jsp";

    private static final Logger log = LoggerFactory.getLogger(WebMvcConfigure.class);

    @Autowired
    private Environment env;

    @Bean(name = "crmDataSource")
    public DataSource crmDataSource() {

        ComboPooledDataSource crmDataSource = new ComboPooledDataSource();

        try {
            crmDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        log.info("jdbc.url=" + env.getProperty("jdbc.url"));
        log.info("jdbc.user=" + env.getProperty("jdbc.user"));

        crmDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        crmDataSource.setUser(env.getProperty("jdbc.user"));
        crmDataSource.setPassword(env.getProperty("jdbc.password"));

        crmDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        crmDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        crmDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        crmDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return crmDataSource;
    }

    @Bean(name = "securityDataSource")
    public DataSource securityDataSource() {

        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        try {
            securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        log.info(">>> security.jdbc.url=" + env.getProperty("security.jdbc.url"));
        log.info(">>> security.jdbc.user=" + env.getProperty("security.jdbc.user"));

        securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
        securityDataSource.setUser(env.getProperty("security.jdbc.user"));
        securityDataSource.setPassword(env.getProperty("security.jdbc.password"));

        securityDataSource
                .setInitialPoolSize(getIntProperty("security.connection.pool.initialPoolSize"));
        securityDataSource
                .setMinPoolSize(getIntProperty("security.connection.pool.minPoolSize"));
        securityDataSource
                .setMaxPoolSize(getIntProperty("security.connection.pool.maxPoolSize"));
        securityDataSource
                .setMaxIdleTime(getIntProperty("security.connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    private int getIntProperty(String propName) {
        return Integer.parseInt(Objects.requireNonNull(env.getProperty(propName)));
    }

    private Properties getHibernateProperties() {

        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        return props;
    }

    @Bean(name = "crmSessionFactory")
    public LocalSessionFactoryBean crmSessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(crmDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean(name = "securitySessionFactory")
    public LocalSessionFactoryBean securitySessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean(name = "crmTransactionManager")
    @Autowired
    public HibernateTransactionManager crmTransactionManager(
            @Qualifier(value = "crmSessionFactory") SessionFactory sessionFactory) {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean(name = "securityTransactionManager")
    @Autowired
    public HibernateTransactionManager securityTransactionManager(
            @Qualifier(value = "securitySessionFactory") SessionFactory sessionFactory) {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(RESOLVER_PREFIX);
        viewResolver.setSuffix(RESOLVER_SUFFIX);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
    }
}
