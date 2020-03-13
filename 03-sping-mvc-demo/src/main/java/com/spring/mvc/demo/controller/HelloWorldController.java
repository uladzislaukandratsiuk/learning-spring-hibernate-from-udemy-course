package com.spring.mvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String showWelcomeForm(HttpServletRequest request, Model model) {

        String theName = request.getParameter("studentName");
        String result = "Welcome, " + theName.toUpperCase() + "!";
        model.addAttribute("message", result);

        return "hello-world";
    }
}
