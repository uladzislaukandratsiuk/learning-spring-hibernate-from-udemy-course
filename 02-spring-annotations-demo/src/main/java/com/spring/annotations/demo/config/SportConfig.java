package com.spring.annotations.demo.config;

import com.spring.annotations.demo.core.Coach;
import com.spring.annotations.demo.core.SwimCoach;
import com.spring.annotations.demo.service.FortuneService;
import com.spring.annotations.demo.service.SadFortuneService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sport.properties")
public class SportConfig {

    @Bean
    public FortuneService sadFortuneService() {
        return new SadFortuneService();
    }

    @Bean
    public Coach swimCoach() {
        return new SwimCoach(sadFortuneService());
    }
}
