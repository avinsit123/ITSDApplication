package com.io.itsduser.dao;

import com.io.dbprops.Dao;
import com.io.itsduser.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component(value = "user")
public class UserDao implements Dao<User> {

    private SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(UserDao.class);

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(User user) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (RuntimeException e) {
            logger.error("User entry in DB failed with exception {}" , e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> get(String hqlQuery) {
        Session session = sessionFactory.openSession();
        List<User> userList = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery(hqlQuery, User.class).getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            logger.error("User Data retrieval from DB failed with reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (RuntimeException e) {
            logger.error("User updation into DB failed with reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
