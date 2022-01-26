package com.io.itsduser.dao;

import com.io.itsduser.model.Customer;

import java.util.Optional;

public interface CustomerDao {

    Optional<String> insertCustomer(Customer customer);
}
