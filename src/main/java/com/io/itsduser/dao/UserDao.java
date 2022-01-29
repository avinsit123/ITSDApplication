package com.io.itsduser.dao;

import com.io.dbprops.Dao;
import com.io.itsduser.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public Optional<String> insert(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            logger.error("User entry in DB failed with exception {}" , e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
        return Optional.ofNullable(user.getId());
    }

    @Override
    public List<User> get(String hqlQuery) {
        Session session = sessionFactory.openSession();
        List<User> userList = new ArrayList<>();
        try {
            session.beginTransaction();
            userList = session.createQuery(hqlQuery, User.class).getResultList();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            logger.error("User Data retrieval from DB failed with reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void deleteUsingId(String id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User userToBeDeleted = session.get(User.class, id);
            session.delete(userToBeDeleted);
        } catch (RuntimeException e) {
            logger.error("User Deletion from DB failed with reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
