package com.io.itsduser.dao;

import com.io.itsduser.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDaoImpl implements CustomerDao {

    private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<String> insertCustomer(Customer customer) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            logger.error("Customer entry in DB failed with exception {}" , e.getMessage());
            logger.error("Printing Stacktrace {}", e.getStackTrace());
            return Optional.empty();
        }

        return Optional.ofNullable(customer.getId());
    }



}
