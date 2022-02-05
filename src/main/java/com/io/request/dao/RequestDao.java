package com.io.request.dao;

import com.io.request.model.Request;

import java.util.List;

public interface RequestDao {

    List<Request> retrieveRequests(String hqlQuery);

    void createRequest(Request request);
}
