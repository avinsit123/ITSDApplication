package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.dao.CustomerDao;
import com.io.itsduser.dao.ResponderDao;
import com.io.itsduser.dao.UserDao;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.Responder;
import com.io.itsduser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.io.TableKt.RESPONDER_TABLE_NAME;
import static com.io.TableKt.USER_TABLE_NAME;

@Service
public class ResponderServiceImpl implements ResponderService{

    private final CustomerDao customerDao;
    private final UserService userService;
    private final HibernateQueryBuilder hibernateQueryBuilder;

    @Autowired
    public ResponderServiceImpl(CustomerDao customerDao, HibernateQueryBuilder hibernateQueryBuilder,
                                UserService userService) {
        this.customerDao = customerDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
        this.userService = userService;
    }

    public void createResponder(@NonNull CreateUserBody createUserBody) {
        Customer customer = userService.getCustomerForLoggedInUser();
        User responder = new User().setId(UUID.randomUUID().toString())
                .setEmail(createUserBody.getEmail())
                .setName(createUserBody.getName())
                .setPassword(createUserBody.getPassword())
                .setRole("RESPONDER");
        customer.getUserList().add(responder);
        customerDao.update(customer);
    }

    public List<User> getAllResponders() {
        hibernateQueryBuilder.flush();
        Customer customer = userService.getCustomerForLoggedInUser();
        return customer.getUserList().stream()
                .filter(user -> "RESPONDER".equals(user.getRole().trim()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
