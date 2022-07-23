package com.io.request.services;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.model.User;
import com.io.itsduser.service.UserService;
import com.io.login.LoginUtils;
import com.io.request.dao.RequestDao;
import com.io.request.model.Request;
import com.io.request.model.types.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.io.TableKt.REQUEST_TABLE_NAME;

@Component
public class RequestServiceImpl implements RequestService {

    private final RequestDao requestDao;
    private final UserService userService;
    private final HibernateQueryBuilder hibernateQueryBuilder;

    @Autowired
    public RequestServiceImpl(RequestDao requestDao, HibernateQueryBuilder hibernateQueryBuilder, UserService userService) {
        this.requestDao = requestDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
        this.userService = userService;
    }

    @Override
    public Request getRequestById(String requestId) {
       hibernateQueryBuilder.flush();
       String getRequestByIdHqlQuery = hibernateQueryBuilder.setTableName(REQUEST_TABLE_NAME)
               .addEqualityFilter("id", requestId)
               .addOrderBy("id", true)
               .returnHqlQuery();
       return requestDao.get(getRequestByIdHqlQuery).get(0);
    }

    @Override
    public List<Request> getAllRequests() {
       hibernateQueryBuilder.flush();
        String getAllRequestHQLQuery = hibernateQueryBuilder.setTableName(REQUEST_TABLE_NAME)
                .returnHqlQuery();
        return requestDao.get(getAllRequestHQLQuery);
    }

    @Override
    public List<Request> getAllRequestsForLoggedInUser() {
        return userService.getLoggedInUser().getRequestList();
    }

    @Override
    public void selfAssignRequest(String requestId) {
        User currentUser = userService.getLoggedInUser();
        Request request = getRequestById(requestId);
        request.setAssigneeName(currentUser.getName());
        requestDao.update(request);
    }

    @Override
    public void unAssignRequest(String requestId) {
        Request request = getRequestById(requestId);
        User currentUser = userService.getLoggedInUser();
        if(currentUser.getName().equals(request.getAssigneeName())) {
            request.setAssigneeName("UNASSIGNED");
            requestDao.update(request);
        }
    }

    @Override
    public void modifyRequestState(String requestId, String statusId) {
        Request request = getRequestById(requestId);
        String statusName = RequestStatus.retrieveStatusNameForStatusId(statusId);
        request.setStatus(statusName);
        requestDao.update(request);
    }

}
