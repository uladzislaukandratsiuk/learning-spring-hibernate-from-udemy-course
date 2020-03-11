package com.demo.spring.core.core_logic;

import com.demo.spring.core.service.FortuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackCoach implements Coach {

	private static Logger log = LoggerFactory.getLogger(TrackCoach.class);

	private FortuneService fortuneService;

	public TrackCoach() {}

	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return "Just do it:" + fortuneService.getFortune();
	}

	public void preInit() {
		log.info("Init method after properties are set");
	}

	public void cleanUp() {
		log.info("Spring Container is destroy! Clean up");
	}
}
