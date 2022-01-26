package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.dao.CustomerDao;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.itsduser.model.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerServiceImpl implements CustomerService{

    private final CustomerDao customerDao;
    private final UserService userService;
    private HibernateQueryBuilder hibernateQueryBuilder;

    private static final String CUSTOMER_TABLE_NAME = "customer";

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao,
                               UserService userService,
                               HibernateQueryBuilder hibernateQueryBuilder) {
        this.customerDao = customerDao;
        this.userService = userService;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
    }

    public void createCustomer(CreateCustomerBody createCustomerBody) {
        Customer newCustomer = new Customer().setId(UUID.randomUUID().toString())
                .setName(createCustomerBody.getName())
                .setEmail(createCustomerBody.getOwnerEmail());

        User ownerUser = new User().setId(UUID.randomUUID().toString())
                .setCustomerId(newCustomer.getId())
                .setPassword(createCustomerBody.getOwnerPassword())
                .setName(createCustomerBody.getOwnerName())
                .setRole(UserRole.OWNER.toString());

        customerDao.insertCustomer(newCustomer);
        userService.createUser(ownerUser);
    }
}
