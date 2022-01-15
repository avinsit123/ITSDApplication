package com.io.itsd.services;

import com.io.itsd.controller.data.CreateRequestBody;
import com.io.itsd.dao.RequestDao;
import com.io.itsd.model.Request;
import com.io.itsd.model.types.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void createRequest(CreateRequestBody createRequestBody) {

        Request newRequest = new Request().setAssigneeName("None")
                .setId(UUID.randomUUID().toString())
                .setStatus(RequestStatus.OPEN.toString())
                .setCreationTime(OffsetDateTime.now().toString())
                .setUpdationTime(OffsetDateTime.now().toString())
                .setCustomer(createRequestBody.getCustomer())
                .setDescription(createRequestBody.getDescription())
                .setTitle(createRequestBody.getTitle());

        requestDao.createRequest(newRequest);
    }

}
