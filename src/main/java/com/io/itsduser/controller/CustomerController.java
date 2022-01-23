package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class CustomerController {

    private CustomerService customerService;
    private static final String CUSTOMER_BASE_URL = "/customer";

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/create")
    public void createCustomer(CreateCustomerBody createCustomerBody) {
        customerService.createCustomer(createCustomerBody);
    }

}
