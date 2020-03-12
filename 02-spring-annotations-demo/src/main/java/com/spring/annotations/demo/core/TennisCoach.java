package com.spring.annotations.demo.core;

import com.spring.annotations.demo.service.FortuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class TennisCoach implements Coach, DisposableBean {

    private static Logger log = LoggerFactory.getLogger(TennisCoach.class);

    private FortuneService fortuneService;

    @Autowired
    public TennisCoach(@Qualifier("randomFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    @PostConstruct
    public void preInit() {
        log.info("Init method after properties are set! Memory location: " + this);
    }

    @PreDestroy
    public void cleanUp() {
        log.info("Spring Container is destroy! Clean up! -> but not for prototype Beans");
    }

    @Override
    public void destroy() throws Exception {
        log.info("Spring Container is destroy! Clean up! -> for prototype Beans");
    }
}
