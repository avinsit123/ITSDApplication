package com.io.request.controller;

import com.io.request.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentController {


    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



}
