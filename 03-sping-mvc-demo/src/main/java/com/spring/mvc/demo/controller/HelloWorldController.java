package com.spring.mvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("welcome-form")
    public String showWelcomeForm(@RequestParam("studentName") String theName, Model model) {

        String result = "Welcome, " + theName.toUpperCase() + "!";
        model.addAttribute("message", result);

        return "hello-world";
    }
}
