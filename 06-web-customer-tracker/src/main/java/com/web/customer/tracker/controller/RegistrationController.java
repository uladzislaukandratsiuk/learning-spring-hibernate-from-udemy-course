package com.web.customer.tracker.controller;

import com.web.customer.tracker.entity.User;
import com.web.customer.tracker.service.UserService;
import com.web.customer.tracker.user.CrmUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registration-form")
    public String showMyLoginPage(Model model) {
        model.addAttribute("crmUser", new CrmUser());
        return "registration-form";
    }

    @PostMapping("/registration-confirmation")
    public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser,
                                          BindingResult bindingResult, Model model) {
        String userName = crmUser.getUserName();
        log.info("Processing registration form for: " + userName);

        if (bindingResult.hasErrors()) return "registration-form";

        User existingUser = userService.findUserByName(userName);
        if (existingUser != null) {
            model.addAttribute("crmUser", new CrmUser());
            model.addAttribute("registrationError", "User name already exists.");
            log.warn("User name already exists.");
            return "registration-form";
        }

        userService.save(crmUser);
        log.info("Successfully created user: " + userName);

        return "registration-confirmation";
    }
}
