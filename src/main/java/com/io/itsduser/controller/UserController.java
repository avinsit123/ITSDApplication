package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.model.Customer;
import com.io.itsduser.service.CustomerService;
import com.io.itsduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class UserController {

    private final CustomerService customerService;
    private final UserService userService;

    private static final String USER_BASE_URL = "/customer";

    @Autowired
    public UserController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping(value = USER_BASE_URL + "/create")
    public String createUser(Model model) {
        List<Customer> allCustomers = customerService.getAllCustomers();
        model.addAttribute("customerList", allCustomers);
        model.addAttribute("createUserBody", new CreateUserBody());
        return "CreateUser";
    }

    public String insertUser(@ModelAttribute CreateUserBody createUserBody) {
        userService.createUser(createUserBody);
        return "hello";
    }

}
