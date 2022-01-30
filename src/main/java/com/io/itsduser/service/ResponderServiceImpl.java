package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.dao.ResponderDao;
import com.io.itsduser.model.Responder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResponderServiceImpl implements ResponderService{

    private ResponderDao responderDao;
    private HibernateQueryBuilder hibernateQueryBuilder;

    public ResponderServiceImpl(ResponderDao responderDao, HibernateQueryBuilder hibernateQueryBuilder) {
        this.responderDao = responderDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
    }

    public void createResponder(@NonNull CreateUserBody createUserBody) {
        Responder responder = new Responder().setId(UUID.randomUUID().toString())
                .setEmail(createUserBody.getEmail())
                .setName(createUserBody.getName())
                .setPassword(createUserBody.getPassword());
        responderDao.insert(responder);
    }
}
