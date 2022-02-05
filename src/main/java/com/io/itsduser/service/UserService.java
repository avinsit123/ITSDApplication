package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.request.controller.data.CreateRequestBody;
import org.springframework.lang.NonNull;

public interface UserService {

    void createUser(CreateUserBody createUserBody);

    void createRequestForUser(CreateRequestBody createRequestBody);
}
