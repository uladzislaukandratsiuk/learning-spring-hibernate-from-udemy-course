package com.springboot.overview.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SimpleRestController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello Spring Boot + DevTools! Time on server is " + LocalDateTime.now();
    }

    @GetMapping("/dev-tools")
    public String devTools() {
        return "Automatic reloading with DevTools!";
    }
}
