package com.web.customer.tracker.controller;

import com.web.customer.tracker.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("list")
    public String listOfCustomers(Model model) {
        model.addAttribute("customers", customerDAO.getCustomers());
        return "list-customers";
    }
}
