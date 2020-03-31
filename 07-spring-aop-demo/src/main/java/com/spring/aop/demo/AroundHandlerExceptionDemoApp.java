package com.spring.aop.demo;

import com.spring.aop.demo.config.DemoConfig;
import com.spring.aop.demo.service.TrafficFortuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AroundHandlerExceptionDemoApp {

    private static Logger log = LoggerFactory.getLogger(AroundHandlerExceptionDemoApp.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        log.info("Get fortune result: {}", fortuneService.getFortune(true));

        context.close();
    }
}
