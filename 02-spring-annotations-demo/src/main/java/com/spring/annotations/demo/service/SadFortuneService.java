package com.spring.annotations.demo.service;

public class SadFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "Today is sad day...";
    }
}
