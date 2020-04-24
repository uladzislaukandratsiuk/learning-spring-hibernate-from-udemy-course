package com.spring.rest.crm.service;

import com.spring.rest.crm.entity.Customer;

import java.util.List;

public interface CustomerService {

	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void deleteCustomer(int id);

}
