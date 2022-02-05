package com.io.request.services;

import com.io.request.controller.data.CreateRequestBody;
import com.io.request.model.Request;

import java.util.List;

public interface RequestService {

    void createRequest(CreateRequestBody createRequestBody);

    Request getRequestById(String requestId);

    List<Request> getAllRequests();
}
