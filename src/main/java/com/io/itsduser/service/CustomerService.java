package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.controller.model.UpdateCustomerBody;
import com.io.itsduser.model.Customer;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {

    void createCustomer(CreateCustomerBody createCustomerBody);

    void updateCustomerWithNewUser(CreateUserBody createUserBody);

    List<Customer> getAllCustomers();

    Customer retrieveCustomerUsingName(String name);

    Customer retrieveCustomerUsingId(String id);

    Customer get(String id);

    void updateCustomer(UpdateCustomerBody updateCustomerBody);

    void deleteCustomer(String id);
}
