package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.dao.CustomerDao;
import com.io.itsduser.dao.UserDao;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.Responder;
import com.io.itsduser.model.User;
import com.io.request.controller.data.CreateRequestBody;
import com.io.request.model.Request;
import com.io.request.model.types.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private CustomerService customerService;
    private HibernateQueryBuilder hibernateQueryBuilder;

    private static final String USER_TABLE_NAME = "itsd_user";

    @Autowired
    public UserServiceImpl(UserDao userDao, HibernateQueryBuilder hibernateQueryBuilder,
                           CustomerService customerService) {
        this.userDao = userDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
        this.customerService = customerService;
    }

    public void createUser(@NonNull CreateUserBody createUserBody) {

        User user = new User().setId(UUID.randomUUID().toString())
                .setName(createUserBody.getName())
                .setEmail(createUserBody.getEmail())
                .setPassword(createUserBody.getPassword())
                .setRole(createUserBody.getRole());

        userDao.insert(user);
    }

    public void createRequestForUser(@NonNull CreateRequestBody createRequestBody) {
        hibernateQueryBuilder.flush();

        String userId = createRequestBody.getUserId();
        hibernateQueryBuilder.setTableName(USER_TABLE_NAME)
                .addEqualityFilter("id",  userId);

        User user = userDao.get(hibernateQueryBuilder.returnHqlQuery()).get(0);

        Request newRequest = new Request().setAssigneeName("None")
                .setId(UUID.randomUUID().toString())
                .setStatus(RequestStatus.OPEN.toString())
                .setCreationTime(OffsetDateTime.now().toString())
                .setUpdationTime(OffsetDateTime.now().toString())
                .setDescription(createRequestBody.getDescription())
                .setTitle(createRequestBody.getTitle());

        user.addRequest(newRequest);
        userDao.update(user);
    }
}
