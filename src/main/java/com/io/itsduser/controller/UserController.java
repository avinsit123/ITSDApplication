package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.controller.model.UpdateUserBody;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.itsduser.service.CustomerService;
import com.io.itsduser.service.UserService;
import com.io.request.controller.data.CreateRequestBody;
import com.io.request.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final CustomerService customerService;
    private final UserService userService;
    private static final String USER_BASE_URL = "/user";

    @Autowired
    public UserController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping(value = USER_BASE_URL + "/create")
    public String createUser(Model model) {
        List<Customer> allCustomers = customerService.getAllCustomers();
        List<String> allCustomerNames = allCustomers.stream()
                .map(Customer::getName)
                .collect(Collectors.toList());
        model.addAttribute("customerNames", allCustomerNames);
        model.addAttribute("createUserBody", new CreateUserBody());
        return "CreateUser";
    }

    @GetMapping(value = USER_BASE_URL + "/{customerId}/create")
    public String createUserWithCustomerId(Model model, @PathVariable String customerId) {
        model.addAttribute("createUserBody", new CreateUserBody().setCustomerId(customerId));
        return "CreateUser";
    }


    @PostMapping(value = USER_BASE_URL + "/insert")
    public String insertUser(@ModelAttribute CreateUserBody createUserBody) {
        customerService.updateCustomerWithNewUser(createUserBody);
        return "redirect:customer/" + createUserBody.getCustomerId() + "/user";
    }

    @GetMapping(value = USER_BASE_URL + "/detail/{id}")
    public String getUserDetails(Model model, @PathVariable String id) {
        User user = new User();
        model.addAttribute("user", user);
        return "UserDetail";
    }

    @GetMapping(value = USER_BASE_URL + "/{id}/update")
    public String populateFormToUpdateUser(Model model, @PathVariable String id) {
        User user = userService.getUser(id);
        UpdateUserBody updateUserBody = new UpdateUserBody().setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail());
        model.addAttribute("updateUserBody", updateUserBody);
        return "UpdateUser";
    }

    @PostMapping(value = USER_BASE_URL + "/update")
    public RedirectView updateUser(@ModelAttribute UpdateUserBody updateUserBody) {
        String customerId = userService.getCustomerForUser(updateUserBody.getId()).getId();
        userService.updateUser(updateUserBody);
        return new RedirectView("/customer/" + customerId +" /user");
    }

    @GetMapping(value = USER_BASE_URL + "/{id}/delete")
    public RedirectView deleteUser(@PathVariable String id) {
        String customerId = userService.getCustomerForUser(id).getId();
        userService.deleteUser(id);
        return new RedirectView("/customer/" + customerId +"/user");
    }


}
