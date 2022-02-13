package com.io.itsduser.controller.model;

public class CreateUserBody {

    private String name;

    private String phone;

    private String email;

    private String customerId;

    private String password;

    private String role;

    public String getRole() {
        return role;
    }

    public CreateUserBody setRole(String role) {
        this.role = role;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateUserBody setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CreateUserBody setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserBody setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public CreateUserBody setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserBody setPassword(String password) {
        this.password = password;
        return this;
    }
}
