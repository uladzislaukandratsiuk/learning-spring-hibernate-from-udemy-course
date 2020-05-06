package com.spring.boot.thymeleaf.thymeleafwithhtmlcss.controller;

import com.spring.boot.thymeleaf.thymeleafwithhtmlcss.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees;

    @PostConstruct
    private void loadData() {

        Employee firstEmployee =
                new Employee(1, "Maxim", "Lee", "lee@gmail.com");
        Employee secondEmployee =
                new Employee(2, "Brayden", "Craig", "craig@gmail.com");
        Employee thirdEmployee =
                new Employee(3, "Jensen", "Russell", "russell@gmail.com");

        employees = new ArrayList<>();

        employees.add(firstEmployee);
        employees.add(secondEmployee);
        employees.add(thirdEmployee);
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        model.addAttribute("employees", employees);
        return "list-employees";
    }
}
