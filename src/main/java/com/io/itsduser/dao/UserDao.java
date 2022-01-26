package com.io.itsduser.dao;

import com.io.itsduser.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<String> insertUser(User user);
}
