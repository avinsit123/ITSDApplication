package com.io.itsduser.model;

import com.io.itsd.model.Request;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "responder")
public class Responder {

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




}
