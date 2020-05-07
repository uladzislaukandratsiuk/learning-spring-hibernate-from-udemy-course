package com.thymeleaf.crud.database.service;

import com.thymeleaf.crud.database.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
