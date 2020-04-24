package com.spring.rest.crm.service;

import com.spring.rest.crm.dao.CustomerDAO;
import com.spring.rest.crm.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional("crmTransactionManager")
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional("crmTransactionManager")
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional("crmTransactionManager")
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional("crmTransactionManager")
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
	}
}
