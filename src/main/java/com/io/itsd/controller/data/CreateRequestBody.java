package com.io.itsd.controller.data;

public class CreateRequestBody {

    private String title;

    private String customer;

    private String description;

    public CreateRequestBody setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateRequestBody setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public CreateRequestBody setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getCustomer() {
        return customer;
    }

    public String getDescription() {
        return description;
    }
}
