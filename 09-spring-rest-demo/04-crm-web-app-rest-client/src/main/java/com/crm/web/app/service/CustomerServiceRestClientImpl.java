package com.crm.web.app.service;

import com.crm.web.app.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerServiceRestClientImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceRestClientImpl.class);

    private final RestTemplate restTemplate;

    private final String crmRestUrl;

    @Autowired
    public CustomerServiceRestClientImpl(RestTemplate restTemplate,
                                         @Value("${crm.rest.server.url}") String crmRestUrl) {
        this.restTemplate = restTemplate;
        this.crmRestUrl = crmRestUrl;
        log.info("Loaded property: crm.rest.server.url=" + crmRestUrl);
    }

    @Override
    public List<Customer> getCustomers() {
        log.info("in getCustomers(): Calling REST API " + crmRestUrl);

        // make REST call
        ResponseEntity<List<Customer>> responseEntity =
                restTemplate.exchange(crmRestUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});

        List<Customer> customers = responseEntity.getBody();
        log.info("in getCustomers(): customers=" + customers);

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        log.info("in saveCustomer(): Calling REST API " + crmRestUrl);

        // make REST call
        if (customer.getId() == 0) {
            // add employee
            restTemplate.postForEntity(crmRestUrl, customer, String.class);
        } else {
            // update employee
            restTemplate.put(crmRestUrl, customer);
        }
        log.info("in saveCustomer(): success");
    }

    @Override
    public Customer getCustomer(int id) {
        log.info("in getCustomer(): Calling REST API " + crmRestUrl);

        // make REST call
        Customer customer =
                restTemplate.getForObject(crmRestUrl + "/" + id, Customer.class);

        log.info("in saveCustomer(): customer=" + customer);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        log.info("in deleteCustomer(): Calling REST API " + crmRestUrl);

        // make REST call
        restTemplate.delete(crmRestUrl + "/" + id);

        log.info("in deleteCustomer(): deleted customer with id=" + id);
    }
}
