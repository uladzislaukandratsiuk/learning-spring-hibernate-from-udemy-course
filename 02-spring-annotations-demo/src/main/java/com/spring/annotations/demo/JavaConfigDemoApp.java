package com.spring.annotations.demo;

import com.spring.annotations.demo.config.SportConfig;
import com.spring.annotations.demo.core.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

    public static Logger log = LoggerFactory.getLogger(JavaConfigDemoApp.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(SportConfig.class);

        Coach coach = context.getBean("tennisCoach", Coach.class);

        log.info("Annotations DI with constructor -> daily workout: {}",
                coach.getDailyWorkout());
        log.info("Annotations DI with constructor -> daily fortune: {}",
                coach.getDailyFortune());

        context.close();
    }
}
