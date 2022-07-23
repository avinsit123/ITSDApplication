package com.io.request.services;

import com.io.request.model.Request;

import java.util.List;

public interface RequestService {


    Request getRequestById(String requestId);

    List<Request> getAllRequests();

    List<Request> getAllRequestsForLoggedInUser();

    void selfAssignRequest(String requestId);

    void unAssignRequest(String requestId);

    void modifyRequestState(String requestId, String requestState);
}
