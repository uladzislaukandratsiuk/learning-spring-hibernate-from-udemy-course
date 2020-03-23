package com.web.customer.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @RequestMapping("list")
    public String listOfCustomers(Model model) {
        return "list-customers";
    }
}
