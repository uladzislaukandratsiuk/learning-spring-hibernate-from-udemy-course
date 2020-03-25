package com.spring.aop.demo;

import com.spring.aop.demo.aspect.MyDemoLoggingAspect;
import com.spring.aop.demo.config.DemoConfig;
import com.spring.aop.demo.dao.AccountDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    private static Logger log = LoggerFactory.getLogger(MainDemoApp.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO bean = context.getBean("accountDAO", AccountDAO.class);

        bean.addAccount();

        log.info("call addAccount() method again...");

        bean.addAccount();

        context.close();
    }
}
