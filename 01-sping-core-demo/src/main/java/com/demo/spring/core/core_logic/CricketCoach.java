package com.demo.spring.core.core_logic;

import com.demo.spring.core.service.FortuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CricketCoach implements Coach {

    private static Logger log = LoggerFactory.getLogger(CricketCoach.class);

    private FortuneService fortuneService;

    public CricketCoach() {
        log.info("CricketCoach: inside no-arg constructor");
    }

    public void setFortuneService(FortuneService fortuneService) {
        log.info("CricketCoach: inside setter method setFortuneService");
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
