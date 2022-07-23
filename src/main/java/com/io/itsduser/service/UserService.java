package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.controller.model.UpdateUserBody;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.request.controller.data.CreateRequestBody;
import com.io.request.model.Request;

import java.util.List;

public interface UserService {

    void createUser(CreateUserBody createUserBody);

    void createRequestForUser(CreateRequestBody createRequestBody);

    List<Request> getAllRequestsForUser(String userId);

    User getUser(String userId);

    Request insertRequest(CreateRequestBody createRequestBody);

    Customer getCustomerForLoggedInUser();

    User getLoggedInUser();

    User getUserByUsername(String username);

    void updateUser(UpdateUserBody updateUserBody);

    void deleteUser(String userId);

    Customer getCustomerForUser(String userId);
}
