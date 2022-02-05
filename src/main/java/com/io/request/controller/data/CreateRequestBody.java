package com.io.request.controller.data;

public class CreateRequestBody {

    private String title;

    private String description;

    private String userId;

    public CreateRequestBody setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateRequestBody setDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateRequestBody setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
