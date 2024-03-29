package com.io.request.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "user_name")
    private String userName;

    public String getId() {
        return id;
    }

    public Comment setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Comment setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Comment setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Comment setUserName(String userId) {
        this.userName = userId;
        return this;
    }
}
