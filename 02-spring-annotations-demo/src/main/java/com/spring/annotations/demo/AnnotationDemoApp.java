package com.spring.annotations.demo;

import com.spring.annotations.demo.core.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

    public static Logger log = LoggerFactory.getLogger(AnnotationDemoApp.class);

    public static final String CONFIG_LOCATION = "applicationContext.xml";

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        Coach coach = context.getBean("tennisCoach", Coach.class);

        log.info("Annotations DI with constructor -> daily workout: {}",
                coach.getDailyWorkout());
        log.info("Annotations DI with constructor -> daily fortune: {}",
                coach.getDailyFortune());

        context.close();
    }
}
