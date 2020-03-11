package com.demo.spring.core;

import com.demo.spring.core.core_logic.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeApp {

    public static Logger log = LoggerFactory.getLogger(BeanScopeApp.class);

    public static final String CONFIG_LOCATION = "beanScope-applicationContext.xml";

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        Coach firstCoach = context.getBean("myCoach", Coach.class);

        Coach secondCoach = context.getBean("myCoach", Coach.class);

        boolean result = (firstCoach == secondCoach);

        log.info("firstCoach reference pointing " +
                "to the same object as secondCoach is: {}", result);
        log.info("Memory location for firstCoach: {}", firstCoach);
        log.info("Memory location for secondCoach: {}", secondCoach);

        context.close();
    }
}
