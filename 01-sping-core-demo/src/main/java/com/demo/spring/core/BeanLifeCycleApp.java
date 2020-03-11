package com.demo.spring.core;

import com.demo.spring.core.core_logic.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleApp {

    public static Logger log = LoggerFactory.getLogger(BeanLifeCycleApp.class);

    public static final String CONFIG_LOCATION = "beanLifeCycle-applicationContext.xml";

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        Coach coach = context.getBean("myCoach", Coach.class);

        log.info("daily workout: {}", coach.getDailyWorkout());
        log.info("daily fortune: {}", coach.getDailyFortune());

        context.close();
    }
}
