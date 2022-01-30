package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.itsduser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = CUSTOMER_BASE_URL + "/list")
    public String listCustomer(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "ViewCustomers";
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/{id}" + "/user")
    public String listUsersForACustomer(Model model, @PathVariable String id) {
        Customer customer = customerService.retrieveCustomerUsingId(id);
        List<User> userList = customer.getUserList();
        model.addAttribute("userList", userList);
        return "ViewUsers";
    }

}
