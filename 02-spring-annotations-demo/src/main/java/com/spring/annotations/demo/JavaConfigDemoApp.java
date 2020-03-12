package com.spring.annotations.demo;

import com.spring.annotations.demo.config.SportConfig;
import com.spring.annotations.demo.core.SwimCoach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

    public static Logger log = LoggerFactory.getLogger(JavaConfigDemoApp.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(SportConfig.class);

        SwimCoach coach = context.getBean("swimCoach", SwimCoach.class);

        log.info("Annotations with java code -> daily workout: {}",
                coach.getDailyWorkout());
        log.info("Annotations with java code -> daily fortune: {}",
                coach.getDailyFortune());
        log.info("Literal Values Injection -> email address: {}, team: {}",
                coach.getEmailAddress(), coach.getTeam());

        context.close();
    }
}
