package com.web.customer.tracker.service;

import com.web.customer.tracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    void saveCustomer(Customer customer);
}
