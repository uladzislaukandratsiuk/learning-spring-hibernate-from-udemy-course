package com.demo.spring.core.core_logic;

import com.demo.spring.core.service.FortuneService;

public class BaseballCoach implements Coach {

	private FortuneService fortuneService;

	public BaseballCoach() {}

	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}
