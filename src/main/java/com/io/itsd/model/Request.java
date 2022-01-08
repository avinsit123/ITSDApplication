package com.io.itsd.model;

import com.io.itsd.model.types.RequestStatus;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="request")
public class Request {

    @Id
    @Column(name="id")

    private String id;

    @Column(name = "assignee_name")
    private String assigneeName;

    @Column(name = "status")
    private RequestStatus status;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Time creationTime;

    @Column(name = "updated_at")
    private Time updationTime;

    @Column(name = "customer")
    private String Customer;
}
