package com.rest.crud.api.rest;

import com.rest.crud.api.entity.Employee;
import com.rest.crud.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable int id) {

        Employee employee = employeeService.findById(id);

        if (employee == null) throw new RuntimeException("Employee with id=" + id + " not found!");

        return employee;
    }

    @PostMapping("/employees")
    public Employee add(@RequestBody Employee employee) {

        employee.setId(0);

        employeeService.save(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee save(@RequestBody Employee employee) {

        employeeService.save(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteById(@PathVariable int id) {

        Employee employee = employeeService.findById(id);

        if (employee == null) throw new RuntimeException("Employee with id=" + id + " not found!");

        employeeService.deleteById(id);

        return "Deleted employee with id=" + id;
    }
}
