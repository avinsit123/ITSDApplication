package com.io.itsduser.controller.model;

public class CreateCustomerBody {

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public CreateCustomerBody setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateCustomerBody setEmail(String email) {
        this.email = email;
        return this;
    }
}
