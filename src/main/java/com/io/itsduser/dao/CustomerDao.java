package com.io.itsduser.dao;

import com.io.dbprops.Dao;
import com.io.itsduser.model.Customer;
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

@Component(value = "customer")
public class CustomerDao implements Dao<Customer> {

    private static final Logger logger = LogManager.getLogger(CustomerDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Customer customer) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
            logger.info("Inserted customer with id: {} into DB", customer.getId());
        } catch (RuntimeException e) {
            logger.error("Customer entry in DB failed with exception {}" , e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Customer> get(String hqlQuery) {
        Session session = sessionFactory.openSession();
        List<Customer> customerList = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            customerList = session.createQuery(hqlQuery, Customer.class).getResultList();
            transaction.commit();
            logger.info("Retrieved {} customers from DB", customerList.size());
        } catch (RuntimeException e) {
            logger.error("Customer Retrieval from DB failed due to reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerList;
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        } catch (RuntimeException e) {
            logger.error("Customer updation into DB failed with reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String id) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            transaction.commit();
        } catch (RuntimeException e) {
            logger.error("Customer Deletion from DB failed with reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
