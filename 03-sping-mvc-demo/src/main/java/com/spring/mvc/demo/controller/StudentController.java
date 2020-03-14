package com.spring.mvc.demo.controller;

import com.spring.mvc.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @Value("#{${countryOptions}}")
    private Map<String,String> countryOptions;

    @RequestMapping("show-form")
    public String showForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("countryOptions", countryOptions);
        return "student-form";
    }

    @RequestMapping("process-form")
    public String showProcessForm(@ModelAttribute("student") Student student) {
        log.info("student: {}", student);
        String value = countryOptions.get(student.getCountryCode());
        student.setCountryValue(value);
        return "student-confirmation";
    }
}
