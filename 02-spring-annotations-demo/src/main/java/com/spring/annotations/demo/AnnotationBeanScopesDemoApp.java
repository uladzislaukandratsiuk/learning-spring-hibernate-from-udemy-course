package com.spring.annotations.demo;

import com.spring.annotations.demo.core.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopesDemoApp {

    public static Logger log = LoggerFactory.getLogger(AnnotationBeanScopesDemoApp.class);

    public static final String CONFIG_LOCATION = "applicationContext.xml";

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        Coach firstCoach = context.getBean("tennisCoach", Coach.class);

        Coach secondCoach = context.getBean("tennisCoach", Coach.class);

        boolean result = (firstCoach == secondCoach);

        log.info("firstCoach reference pointing " +
                "to the same object as secondCoach is: {}", result);
        log.info("Memory location for firstCoach: {}", firstCoach);
        log.info("Memory location for secondCoach: {}", secondCoach);

        context.close();
    }
}
