package com.io.request.services;

import com.io.itsd.HibernateQueryBuilder;
import com.io.itsduser.service.UserService;
import com.io.request.dao.RequestDao;
import com.io.request.model.Comment;
import com.io.request.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CommentServiceImpl implements CommentService {

    private final RequestService requestService;
    private final UserService userService;
    private final RequestDao requestDao;

    @Autowired
    public CommentServiceImpl(RequestService requestService, UserService userService, RequestDao requestDao) {
        this.requestService = requestService;
        this.userService = userService;
        this.requestDao = requestDao;
    }

    public List<Comment> getAllCommentsForRequest(String requestId) {
        return requestService.getRequestById(requestId)
                .getCommentList();
    }

    public List<Comment> updateRequestWithComment(String requestId, String commentLine) {
        Comment comment = new Comment()
                .setId(UUID.randomUUID().toString())
                .setUserName(userService.getLoggedInUser().getName())
                .setDescription(commentLine)
                .setCreatedAt(returnCurrentDateTime());
        Request request = requestService.getRequestById(requestId)
                .updateCommentList(comment);
        requestDao.update(request);
        return request.getCommentList();
    }

    private String returnCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
