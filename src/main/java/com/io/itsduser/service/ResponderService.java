package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.model.Responder;
import com.io.itsduser.model.User;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ResponderService {

    void createResponder(@NonNull CreateUserBody createUserBody);

    List<User> getAllResponders();
}
