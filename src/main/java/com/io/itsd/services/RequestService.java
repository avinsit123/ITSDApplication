package com.io.itsd.services;

import com.io.itsd.controller.data.CreateRequestBody;
import com.io.itsd.model.Request;

import java.util.List;

public interface RequestService {

    void createRequest(CreateRequestBody createRequestBody);

    Request getRequestById(String requestId);

    List<Request> getAllRequests();
}
