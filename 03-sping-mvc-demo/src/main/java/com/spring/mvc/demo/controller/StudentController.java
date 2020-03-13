package com.spring.mvc.demo.controller;

import com.spring.mvc.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @RequestMapping("show-form")
    public String showForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-form";
    }

    @RequestMapping("process-form")
    public String showProcessForm(@ModelAttribute("student") Student student) {
        log.info("student: {}", student);
        return "student-confirmation";
    }
}
