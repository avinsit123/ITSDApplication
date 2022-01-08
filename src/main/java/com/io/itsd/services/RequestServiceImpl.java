package com.io.itsd.services;

import com.io.itsd.dao.RequestDao;
import com.io.itsd.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestServiceImpl implements RequestService {

    private RequestDao requestDao;

    @Autowired
    public RequestServiceImpl(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public List<Request> getAllRequests() {
        return requestDao.retrieveAllRequests();
    }
}
