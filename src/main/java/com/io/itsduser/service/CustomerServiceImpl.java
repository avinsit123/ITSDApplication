package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.dao.CustomerDao;
import com.io.itsduser.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao;
    private HibernateQueryBuilder hibernateQueryBuilder;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, HibernateQueryBuilder hibernateQueryBuilder) {
        this.customerDao = customerDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
    }

    public void createCustomer(CreateCustomerBody createCustomerBody) {
        Customer newCustomer = new Customer().setId(UUID.randomUUID().toString())
                .setName(createCustomerBody.getName())
                .setEmail(createCustomerBody.getEmail());


    }
}
