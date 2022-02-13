package com.io.itsduser.dao;

import com.io.dbprops.Dao;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.Responder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value = "responder")
public class ResponderDao implements Dao<Responder> {

    private SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(ResponderDao.class);

    @Autowired
    public ResponderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Responder responder) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(responder);
            transaction.commit();
            logger.info("Inserted responder with id: {} into DB", responder.getId());
        } catch (RuntimeException e) {
            logger.error("Responder entry in DB failed with exception {}" , e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Responder> get(String hqlQuery) {
        Session session = sessionFactory.openSession();
        List<Responder> responderList = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            responderList = session.createQuery(hqlQuery, Responder.class).getResultList();
            transaction.commit();
            logger.info("Retrieved {} responders from DB", responderList.size());
        } catch (RuntimeException e) {
            logger.error("Responder Retrieval from DB failed due to reason {}", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return responderList;
    }

    @Override
    public void update(Responder responder) {

    }

    @Override
    public void delete(String id) {

    }
}
