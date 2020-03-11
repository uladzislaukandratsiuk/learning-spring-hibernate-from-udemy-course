package com.spring.annotations.demo.service;

import org.springframework.stereotype.Service;

@Service
public class RandomFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "Today is your usual day...";
    }
}
