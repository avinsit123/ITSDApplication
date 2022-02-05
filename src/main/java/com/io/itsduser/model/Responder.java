package com.io.itsduser.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "responder")
public class Responder implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
//            CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "responder_id")
//    private List<Request> requestList;


    public String getId() {
        return id;
    }

    public Responder setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Responder setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Responder setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Responder setPassword(String password) {
        this.password = password;
        return this;
    }
}
