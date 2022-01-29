package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.model.Customer;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {

    void createCustomer(CreateCustomerBody createCustomerBody);

    List<Customer> getAllCustomers();

    Customer retrieveCustomerUsingName(String name);

}
