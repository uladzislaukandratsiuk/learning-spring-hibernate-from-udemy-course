package com.spring.security.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Component
@EnableWebMvc
@ComponentScan(basePackages = "com.spring.security.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String RESOLVER_PREFIX = "/WEB-INF/view/";
    public static final String RESOLVER_SUFFIX = ".jsp";

    private Logger log = LoggerFactory.getLogger(WebMvcConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(RESOLVER_PREFIX);
        viewResolver.setSuffix(RESOLVER_SUFFIX);
        return viewResolver;
    }

    @Bean
    public DataSource securityDataSource() {

        ComboPooledDataSource dataSourcePool = new ComboPooledDataSource();

        try {
            dataSourcePool.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            log.error("PropertyVetoException: {}", e.getMessage());
        }

        log.info("jdbc.driver={}", env.getProperty("jdbc.driver"));
        log.info("jdbc.user={}", env.getProperty("jdbc.user"));

        dataSourcePool.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSourcePool.setUser(env.getProperty("jdbc.user"));
        dataSourcePool.setPassword(env.getProperty("jdbc.password"));

        dataSourcePool.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        dataSourcePool.setMaxPoolSize(getIntProperty("connection.pool.minPoolSize"));
        dataSourcePool.setMinPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        dataSourcePool.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return dataSourcePool;
    }

    private int getIntProperty(String propName) {

        String propValue = env.getProperty(propName);

        return Integer.parseInt(propValue != null ? propValue : "0");
    }
}
