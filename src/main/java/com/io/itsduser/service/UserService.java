package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.model.User;

public interface UserService {

    void createUser(CreateUserBody createUserBody);
}
