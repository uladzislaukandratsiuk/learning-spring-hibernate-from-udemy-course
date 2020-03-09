package com.demo.spring.core;

public class MyApp {

	public static void main(String[] args) {

		Coach firstCoach = new TrackCoach();
		Coach secondCoach = new BaseballCoach();

		System.out.println(firstCoach.getDailyWorkout());
		System.out.println(secondCoach.getDailyWorkout());
	}
}
