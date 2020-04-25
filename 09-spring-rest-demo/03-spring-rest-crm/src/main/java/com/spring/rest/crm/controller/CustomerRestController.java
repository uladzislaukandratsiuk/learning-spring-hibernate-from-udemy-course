package com.spring.rest.crm.controller;

import com.spring.rest.crm.entity.Customer;
import com.spring.rest.crm.exception.CustomerNotFoundException;
import com.spring.rest.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        if (customers == null || customers.isEmpty()) {
            throw new CustomerNotFoundException("No Customers data found!");
        }
        return customers;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id=" + customerId + " not found!");
        }
        return customer;
    }
}
