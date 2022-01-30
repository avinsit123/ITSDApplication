package com.io.itsduser.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "itsd_customer")
public class Customer implements Serializable {

    @Id
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private final List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public Customer updateUserList(User user) {
        userList.add(user);
        return this;
    }

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }
}
