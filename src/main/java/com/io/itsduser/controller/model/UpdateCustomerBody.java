package com.io.itsduser.controller.model;

public class UpdateCustomerBody {

    private String id;

    private String name;

    private String ownerEmail;

    public String getId() {
        return id;
    }

    public UpdateCustomerBody setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateCustomerBody setName(String name) {
        this.name = name;
        return this;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public UpdateCustomerBody setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        return this;
    }
}
