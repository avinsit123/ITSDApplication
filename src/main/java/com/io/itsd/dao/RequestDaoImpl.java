package com.io.itsd.dao;

import com.io.itsd.model.Request;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class RequestDaoImpl implements RequestDao {


    private SessionFactory sessionFactory;

    @Autowired
    public RequestDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Request> retrieveAllRequests() {
         Session session =  sessionFactory.openSession();
         session.beginTransaction();
         List<Request> requestList = session.createQuery("from Request", Request.class).list();
         session.getTransaction().commit();
         return requestList;
    }

    @Override
    public void createRequest(Request request) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(request);
        session.getTransaction().commit();
    }

}
