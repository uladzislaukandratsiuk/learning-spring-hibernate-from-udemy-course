package com.spring.mvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("show-form")
    public String showForm() {
        return "hello-world-form";
    }

    @GetMapping("process-form")
    public String showProcessForm() {
        return "hello-world";
    }
}
