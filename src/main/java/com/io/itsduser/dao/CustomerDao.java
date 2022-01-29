package com.io.itsduser.dao;

import com.io.dbprops.Dao;
import com.io.itsduser.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component(value = "customer")
public class CustomerDao implements Dao<Customer> {

    private static final Logger logger = LogManager.getLogger(CustomerDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<String> insert(Customer customer) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
            logger.info("Inserted customer with id: {} into DB", customer.getId());
        } catch (RuntimeException e) {
            logger.error("Customer entry in DB failed with exception {}" , e.getMessage());
            logger.error("Printing Stacktrace {}", e.getStackTrace());
            return Optional.empty();
        } finally {
            session.close();
        }

        return Optional.ofNullable(customer.getId());
    }

    public List<Customer> get(String hqlQuery) {
        Session session = sessionFactory.openSession();
        List<Customer> customerList = new ArrayList<>();
        try {
            session.beginTransaction();
            customerList = session.createQuery(hqlQuery, Customer.class).getResultList();
            session.getTransaction().commit();
            logger.info("Retrieved {}) customers from DB", customerList.size());
        } catch (RuntimeException e) {
            logger.error("Customer Retrieval from DB failed due to reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerList;
    }
}
