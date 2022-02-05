package com.io.request.model;

import javax.persistence.*;

@Entity
@Table(name="request")
public class Request {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "assignee_name")
    private String assigneeName;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private String creationTime;

    @Column(name = "updated_at")
    private String updationTime;

    @Column(name = "customer")
    private String Customer;

    public Request setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
        return this;
    }

    public Request setStatus(String status) {
        this.status = status;
        return this;
    }

    public Request setTitle(String title) {
        this.title = title;
        return this;
    }

    public Request setDescription(String description) {
        this.description = description;
        return this;
    }

    public Request setCreationTime(String creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public Request setUpdationTime(String updationTime) {
        this.updationTime = updationTime;
        return this;
    }

    public Request setCustomer(String customer) {
        Customer = customer;
        return this;
    }

    public Request setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getUpdationTime() {
        return updationTime;
    }

    public String getCustomer() {
        return Customer;
    }
}
