package com.io.itsduser.dao;

import com.io.itsduser.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<String> insertUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            logger.error("User entry in DB failed with exception {}" , e.getMessage());
            logger.error("Printing Stacktrace {}", e.getStackTrace());
            return Optional.empty();
        }
        return Optional.ofNullable(user.getId());
    }


}
