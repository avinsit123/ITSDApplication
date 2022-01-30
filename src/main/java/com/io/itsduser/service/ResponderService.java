package com.io.itsduser.service;

import com.io.itsduser.controller.model.CreateUserBody;
import org.springframework.lang.NonNull;

public interface ResponderService {

    void createResponder(@NonNull CreateUserBody createUserBody);
}
