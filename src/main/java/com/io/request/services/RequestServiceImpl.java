package com.io.request.services;

import com.io.itsd.HibernateQueryBuilder;
import com.io.request.controller.data.CreateRequestBody;
import com.io.request.dao.RequestDao;
import com.io.request.model.Request;
import com.io.request.model.types.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static com.io.TableKt.REQUEST_TABLE_NAME;

@Component
public class RequestServiceImpl implements RequestService {

    private final RequestDao requestDao;
    private final HibernateQueryBuilder hibernateQueryBuilder;

    @Autowired
    public RequestServiceImpl(RequestDao requestDao, HibernateQueryBuilder hibernateQueryBuilder) {
        this.requestDao = requestDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
    }

    @Override
    public void createRequest(CreateRequestBody createRequestBody) {

        Request newRequest = new Request().setAssigneeName("None")
                .setId(UUID.randomUUID().toString())
                .setStatus(RequestStatus.OPEN.toString())
                .setCreationTime(OffsetDateTime.now().toString())
                .setUpdationTime(OffsetDateTime.now().toString())
                .setDescription(createRequestBody.getDescription())
                .setTitle(createRequestBody.getTitle());

        requestDao.createRequest(newRequest);
    }

    @Override
    public Request getRequestById(String requestId) {
       hibernateQueryBuilder.flush();
       String getRequestByIdHqlQuery = hibernateQueryBuilder.setTableName(REQUEST_TABLE_NAME)
               .addEqualityFilter("id", requestId)
               .addOrderBy("id", true)
               .returnHqlQuery();
       return requestDao.retrieveRequests(getRequestByIdHqlQuery).get(0);
    }

    @Override
    public List<Request> getAllRequests() {
        hibernateQueryBuilder.flush();
        String getAllRequestsHqlQuery = hibernateQueryBuilder.setTableName(REQUEST_TABLE_NAME)
                .returnHqlQuery();
        return requestDao.retrieveRequests(getAllRequestsHqlQuery);
    }

}
