package com.io.itsduser.controller.model;


public class UpdateUserBody {

    private String id;

    private String name;

    private String email;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UpdateUserBody setId(String id) {
        this.id = id;
        return this;
    }

    public UpdateUserBody setName(String name) {
        this.name = name;
        return this;
    }

    public UpdateUserBody setEmail(String email) {
        this.email = email;
        return this;
    }
}
