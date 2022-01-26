package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomerController {

    private CustomerService customerService;
    private static final String CUSTOMER_BASE_URL = "/customer";

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(CUSTOMER_BASE_URL + "/create")
    public String createCustomer(Model model) {
        model.addAttribute("createCustomerBody", new CreateCustomerBody());
        return "CreateCustomer";
    }

    @PostMapping(value = CUSTOMER_BASE_URL + "/insert")
    public String insertCustomer(@ModelAttribute CreateCustomerBody createCustomerBody){
        customerService.createCustomer(createCustomerBody);
        return "hello";
    }

}
