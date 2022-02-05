package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.model.User;
import com.io.request.controller.data.CreateRequestBody;
import com.io.request.model.Request;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {

    void createUser(CreateUserBody createUserBody);

    void createRequestForUser(CreateRequestBody createRequestBody);

    List<Request> getAllRequestsForUser(String userId);

    User getUser(String userId);
}
