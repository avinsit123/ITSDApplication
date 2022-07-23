package com.io.request.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="request")
public class Request implements Serializable {

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    private List<Comment> commentList;

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

    public Request setId(String id) {
        this.id = id;
        return this;
    }

    public Request updateCommentList(Comment comment) {
        this.commentList.add(comment);
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

    public List<Comment> getCommentList() {
        return this.commentList;
    }
}
