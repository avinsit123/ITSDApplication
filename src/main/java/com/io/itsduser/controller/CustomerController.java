package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.controller.model.UpdateCustomerBody;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.itsduser.service.CustomerService;
import com.io.itsduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static com.io.BaseURLsKt.CUSTOMER_BASE_URL;

@Controller
public class CustomerController {

    private CustomerService customerService;
    private UserService userService;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping(CUSTOMER_BASE_URL + "/create")
    public String createCustomer(Model model) {
        model.addAttribute("createCustomerBody", new CreateCustomerBody());
        return "CreateCustomer";
    }

    @PostMapping(value = CUSTOMER_BASE_URL + "/insert")
    public String insertCustomer(@ModelAttribute CreateCustomerBody createCustomerBody){
        customerService.createCustomer(createCustomerBody);
        return "redirect:/homepage";
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/list")
    public String listCustomer(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "ViewCustomers";
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/{id}/update")
    public String populateFormToUpdateCustomer(Model model, @PathVariable String id) {
        Customer customer = customerService.get(id);
        UpdateCustomerBody updateCustomerBody = new UpdateCustomerBody().setId(customer.getId())
                .setName(customer.getName())
                .setOwnerEmail(customer.getEmail());
        model.addAttribute("UpdateCustomerBody", updateCustomerBody);
        return "UpdateCustomer";
    }

    @PostMapping(value = CUSTOMER_BASE_URL + "/update")
    public String updateCustomer(@ModelAttribute UpdateCustomerBody updateCustomerBody) {
        customerService.updateCustomer(updateCustomerBody);
        return "redirect:list";
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return "redirect:list";
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/user/list")
    public String listUsersForACustomer(Model model) {
        Customer customer = userService.getCustomerForLoggedInUser();
        List<User> userList = customer.getUserList();
        model.addAttribute("userList", userList);
        model.addAttribute("customer", customer );
        return "ViewUsers";
    }

}
