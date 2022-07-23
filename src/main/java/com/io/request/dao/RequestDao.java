package com.io.request.dao;

import com.io.dbprops.Dao;
import com.io.itsduser.dao.CustomerDao;
import com.io.itsduser.model.Customer;
import com.io.request.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component(value = "request")
public class RequestDao implements Dao<Request> {

    private static final Logger logger = LogManager.getLogger(RequestDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public RequestDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Request request) {
        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.persist(request);
            transaction.commit();
            logger.info("Inserted requested with id: {} into DB", request.getId());
        } catch (RuntimeException e) {
            logger.error("Responder entry in DB failed with exception {}" , e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Request> get(String hqlQuery) {
        Session session = sessionFactory.openSession();
        List<Request> requestList = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            requestList = session.createQuery(hqlQuery, Request.class).getResultList();
            transaction.commit();
            logger.info("Retrieved {} customers from DB",requestList.size());
        } catch (RuntimeException e) {
            logger.error("Customer Retrieval from DB failed due to reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return requestList;
    }

    @Override
    public void update(Request request) {
        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.update(request);
            transaction.commit();
            logger.info("Inserted requested with id: {} into DB", request.getId());
        } catch (RuntimeException e) {
            logger.error("Responder entry in DB failed with exception {}" , e.getMessage());
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
            Request deletedRequest = session.get(Request.class, id);
            session.delete(deletedRequest);
            transaction.commit();
            logger.info("Deleted requested with id: {} into DB", id);
        } catch (RuntimeException e) {
            logger.error("Request deletion from DB failed with exception {}" , e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
