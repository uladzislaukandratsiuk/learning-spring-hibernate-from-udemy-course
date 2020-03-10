package com.demo.spring.core;

import com.demo.spring.core.core_logic.BaseballCoach;
import com.demo.spring.core.core_logic.Coach;
import com.demo.spring.core.core_logic.TrackCoach;

public class MyApp {

	public static void main(String[] args) {

		Coach firstCoach = new TrackCoach();
		Coach secondCoach = new BaseballCoach();

		System.out.println(firstCoach.getDailyWorkout());
		System.out.println(secondCoach.getDailyWorkout());
	}
}
