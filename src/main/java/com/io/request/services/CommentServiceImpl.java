package com.io.request.services;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.service.RequestService;
import com.io.request.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {

    private final RequestService requestService;
    private final HibernateQueryBuilder hibernateQueryBuilder;

    @Autowired
    public CommentServiceImpl(RequestService userService, HibernateQueryBuilder hibernateQueryBuilder) {
        this.requestService = userService;
        this.hibernateQueryBuilder = hibernateQueryBuilder;
    }

    public List<Comment> getAllCommentsForUser(String userId) {
        List<Comment> commentList = requestService.g
    }
}
