package com.io.itsd.dao;

import com.io.itsd.model.Request;

import java.util.List;

public interface RequestDao {

    List<Request> retrieveRequests(String hqlQuery);

    void createRequest(Request request);
}
