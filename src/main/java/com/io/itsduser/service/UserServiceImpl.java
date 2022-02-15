package com.io.itsduser.service;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.controller.model.UpdateUserBody;
import com.io.itsduser.dao.UserDao;
import com.io.itsduser.model.Customer;
import com.io.itsduser.model.User;
import com.io.request.controller.data.CreateRequestBody;
import com.io.request.model.Request;
import com.io.request.model.status;
import com.io.request.model.types.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static com.io.TableKt.USER_TABLE_NAME;

@Component
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final CustomerService customerService;
    private final HibernateQueryBuilder hibernateQueryBuilder;

    @Autowired
    public UserServiceImpl(UserDao userDao, HibernateQueryBuilder hibernateQueryBuilder,
                           CustomerService customerService) {
        this.userDao = userDao;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
        this.customerService = customerService;
    }

    public void createUser(@NonNull CreateUserBody createUserBody) {

        User user = new User().setId(UUID.randomUUID().toString())
                .setName(createUserBody.getName())
                .setEmail(createUserBody.getEmail())
                .setPassword(createUserBody.getPassword())
                .setRole(createUserBody.getRole());

        userDao.insert(user);
    }

    public void createRequestForUser(@NonNull CreateRequestBody createRequestBody) {
        hibernateQueryBuilder.flush();

        String userId = createRequestBody.getUserId();
        hibernateQueryBuilder.setTableName(USER_TABLE_NAME)
                .addEqualityFilter("id",  userId);

        User user = userDao.get(hibernateQueryBuilder.returnHqlQuery()).get(0);

        Request newRequest = new Request().setAssigneeName("None")
                .setId(UUID.randomUUID().toString())
                .setStatus(RequestStatus.OPEN.toString())
                .setCreationTime(OffsetDateTime.now().toString())
                .setUpdationTime(OffsetDateTime.now().toString())
                .setDescription(createRequestBody.getDescription())
                .setTitle(createRequestBody.getTitle())
                .setStatus(status.OPEN.toString());

        user.addRequest(newRequest);
        userDao.update(user);
    }

    public List<Request> getAllRequestsForUser(String userId) {
        return getUser(userId).getRequestList();
    }

    public void updateUser(UpdateUserBody updateUserBody) {
        User user = this.getUser(updateUserBody.getId());
        user.setName(updateUserBody.getName())
                .setEmail(updateUserBody.getEmail());
        userDao.update(user);
    }

    public User getUser(String userId) {
        hibernateQueryBuilder.flush();
        hibernateQueryBuilder.setTableName(USER_TABLE_NAME)
                .addEqualityFilter("id",  userId);
        return userDao.get(hibernateQueryBuilder.returnHqlQuery()).get(0);
    }

    public void deleteUser(String userId) {
        userDao.delete(userId);
    }

    @Override
    public Customer getCustomerForUser(String userId) {
        List<Customer> customerList = customerService.getAllCustomers();
        for(Customer customer: customerList) {
            for(User user: customer.getUserList()) {
                if(user.getId().equals(userId))
                    return customer;
            }
        }
        return null;
    }
}
