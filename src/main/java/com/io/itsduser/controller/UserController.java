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

    @PostMapping(value = USER_BASE_URL + "/insert")
    public String insertUser(@ModelAttribute CreateUserBody createUserBody) {
        customerService.updateCustomerWithNewUser(createUserBody);
        return "hello";
    }

    @GetMapping(value = USER_BASE_URL + "/detail/{id}")
    public String getUserDetails(Model model, @PathVariable String id) {
        User user = new User();
        model.addAttribute("user", user);
        return "UserDetail";
    }

    @GetMapping(value = USER_BASE_URL + "/{id}/requests")
    public String getAllRequestsForUser(Model model, @PathVariable String id) {
        List<Request> requestList = userService.getAllRequestsForUser(id);
        model.addAttribute("requestList", requestList);
        return "ViewAllRequests";
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
    public String updateUser(@ModelAttribute UpdateUserBody updateUserBody) {
        userService.updateUser(updateUserBody);
        return "hello";
    }

    @GetMapping(value = USER_BASE_URL + "/{id}/delete")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "hello";
    }

    @PostMapping(value = USER_BASE_URL + "/addRequest")
    public String addRequestForUser(@ModelAttribute CreateRequestBody createRequestBody) {
        userService.createRequestForUser(createRequestBody);
        return "hello";
    }

}
