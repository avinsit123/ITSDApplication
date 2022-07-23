package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.controller.model.UpdateUserBody;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.itsduser.service.CustomerService;
import com.io.itsduser.service.UserService;
import com.io.login.LoginUtils;
import com.io.request.controller.data.CreateRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import static com.io.BaseURLsKt.CUSTOMER_BASE_URL;
import static com.io.BaseURLsKt.USER_BASE_URL;

@Controller
public class UserController {

    private final CustomerService customerService;
    private final UserService userService;

    @Autowired
    public UserController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping(value =  CUSTOMER_BASE_URL + USER_BASE_URL + "/create")
    public String displayCreateUserForm(Model model) {
        model.addAttribute("createUserBody", new CreateUserBody());
        return "CreateUser";
    }

    @PostMapping(value = CUSTOMER_BASE_URL + USER_BASE_URL + "/insert")
    public String insertUser(@ModelAttribute CreateUserBody createUserBody) {
        Customer customer = userService.getCustomerForLoggedInUser();
        customerService.updateCurrentCustomerWithNewUser(createUserBody
                .setCustomerId(customer.getId()));
        return "redirect:/homepage";
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
    public String updateUser(@ModelAttribute UpdateUserBody updateUserBody) {
        String customerId = userService.getCustomerForUser(updateUserBody.getId()).getId();
        userService.updateUser(updateUserBody);
        return "redirect:/customer/user/list";
    }

    @GetMapping(value = USER_BASE_URL + "/{id}/delete")
    public String deleteUser(@PathVariable String id) {
        String customerId = userService.getCustomerForUser(id).getId();
        userService.deleteUser(id);
        return "redirect:/customer/user/list" ;

    }

    @GetMapping(value = USER_BASE_URL + "/request/create")
    public String viewCreateRequestForm(Model model) {
        model.addAttribute("createRequestBody", new CreateRequestBody());
        return "CreateRequest";
    }

    @PostMapping(value = USER_BASE_URL + "/request/insert")
    public String insertRequest(CreateRequestBody createRequestBody) {
        userService.insertRequest(createRequestBody);
        return "CreateRequest";
    }

}
