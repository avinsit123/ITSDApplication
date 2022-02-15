package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.controller.model.UpdateCustomerBody;
import com.io.itsduser.dao.CustomerDao;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.itsduser.model.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.io.TableKt.CUSTOMER_TABLE_NAME;

@Component
public class CustomerServiceImpl implements CustomerService{

    private final CustomerDao customerDao;
    private HibernateQueryBuilder hibernateQueryBuilder;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao,
                               HibernateQueryBuilder hibernateQueryBuilder) {
        this.customerDao = customerDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
    }

    @Override
    public void createCustomer(CreateCustomerBody createCustomerBody) {

        User ownerUser = new User().setId(UUID.randomUUID().toString())
                .setPassword(createCustomerBody.getOwnerPassword())
                .setName(createCustomerBody.getOwnerName())
                .setRole(UserRole.OWNER.toString())
                .setEmail(createCustomerBody.getOwnerEmail());

        Customer newCustomer = new Customer().setId(UUID.randomUUID().toString())
                .setName(createCustomerBody.getName())
                .setEmail(createCustomerBody.getOwnerEmail());

        newCustomer.updateUserList(ownerUser);

        customerDao.insert(newCustomer);
    }

    @Override
    public void updateCustomerWithNewUser(CreateUserBody createUserBody) {
        User user = new User().setId(UUID.randomUUID().toString())
                .setName(createUserBody.getName())
                .setEmail(createUserBody.getEmail())
                .setPassword(createUserBody.getPassword())
                .setRole(createUserBody.getRole());
        Customer customer = retrieveCustomerUsingId(createUserBody.getCustomerId());
        customer.updateUserList(user);
        customerDao.update(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        hibernateQueryBuilder.flush();
        final String retrieveAllCustomersHqlQuery = hibernateQueryBuilder.setTableName(CUSTOMER_TABLE_NAME)
                .returnHqlQuery();
        return customerDao.get(retrieveAllCustomersHqlQuery);
    }

    public Customer retrieveCustomerUsingName(String name) {
        return retrieveCustomersWithFilter("name", name).get(0);
    }

    public Customer retrieveCustomerUsingId(String id) {
        return retrieveCustomersWithFilter("id", id).get(0);
    }

    public void updateCustomer(UpdateCustomerBody updateCustomerBody) {
        hibernateQueryBuilder.flush();
        Customer customer = this.get(updateCustomerBody.getId());
        customer.setName(updateCustomerBody.getName())
                .setEmail(updateCustomerBody.getOwnerEmail());
        customerDao.update(customer);
    }

    public Customer get(String id) {
        hibernateQueryBuilder.flush();
        hibernateQueryBuilder.setTableName(CUSTOMER_TABLE_NAME)
                .addEqualityFilter("id",id);
        return customerDao.get(hibernateQueryBuilder.returnHqlQuery()).get(0);
    }

    public void deleteCustomer(String id) {
        customerDao.delete(id);
    }

    private List<Customer> retrieveCustomersWithFilter(String attribute, String cutoffValue) {
        hibernateQueryBuilder.flush();
        hibernateQueryBuilder = hibernateQueryBuilder.setTableName(CUSTOMER_TABLE_NAME);
        hibernateQueryBuilder.addEqualityFilter(attribute, cutoffValue);
        return customerDao.get(hibernateQueryBuilder.returnHqlQuery());
    }

}
