package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.dao.UserDao;
import com.io.itsduser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private HibernateQueryBuilder hibernateQueryBuilder;

    private static final String USER_TABLE_NAME = "user";

    @Autowired
    public UserServiceImpl(UserDao userDao, HibernateQueryBuilder hibernateQueryBuilder) {
        this.userDao = userDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
    }

    public void createUser(User user) {
        userDao.insertUser(user);
    }
}
