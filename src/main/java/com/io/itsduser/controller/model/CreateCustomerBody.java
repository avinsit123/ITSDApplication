package com.io.itsduser.controller.model;

public class CreateCustomerBody {

    private String name;

    private String ownerName;

    private String ownerEmail;

    private String ownerPassword;

    public String getName() {
        return name;
    }

    public CreateCustomerBody setName(String name) {
        this.name = name;
        return this;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public CreateCustomerBody setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public CreateCustomerBody setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        return this;
    }

    public String getOwnerPassword() {
        return ownerPassword;
    }

    public CreateCustomerBody setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
        return this;
    }
}
