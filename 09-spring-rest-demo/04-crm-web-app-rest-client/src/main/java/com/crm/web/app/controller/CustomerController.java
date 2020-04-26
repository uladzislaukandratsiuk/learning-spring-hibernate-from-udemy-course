package com.crm.web.app.controller;

import com.crm.web.app.model.Customer;
import com.crm.web.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {

        List<Customer> theCustomers = customerService.getCustomers();
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/update")
    public String updateCustomer(@RequestParam("customerId") int id, Model model) {

        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id) {

        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }
}
