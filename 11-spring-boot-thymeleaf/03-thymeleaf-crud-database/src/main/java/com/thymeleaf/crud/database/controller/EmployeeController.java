package com.thymeleaf.crud.database.controller;

import com.thymeleaf.crud.database.entity.Employee;
import com.thymeleaf.crud.database.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list-employees";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/form-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/update-employee")
    public String updateEmployee(@ModelAttribute("employeeId") int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/form-employee";
    }

    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
