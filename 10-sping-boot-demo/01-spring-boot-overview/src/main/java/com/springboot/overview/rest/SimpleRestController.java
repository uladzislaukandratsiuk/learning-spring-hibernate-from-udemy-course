package com.springboot.overview.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SimpleRestController {

    @Value("${instructor.name}")
    private String instructorName;

    @Value("${course.name}")
    private String courseName;

    @GetMapping("/")
    public String sayHello() {
        return "Hello Spring Boot + DevTools! Time on server is " + LocalDateTime.now();
    }

    @GetMapping("/dev-tools")
    public String devTools() {
        return "Automatic reloading with DevTools!";
    }

    @GetMapping("/course-info")
    public String getCourseInfo() {
        return "Instructor: " + instructorName + ", Course: " + courseName;
    }
}
