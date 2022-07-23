package com.io.request.controller;

import com.io.request.model.Comment;
import com.io.request.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {


    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/comment/{requestId}")
    public String retrieveComments(Model model, @PathVariable String requestId) {
        List<Comment> commentList = commentService.getAllCommentsForRequest(requestId);
        model.addAttribute("commentList", commentList);
        model.addAttribute("comment", new Comment());
        return "ViewComment";
    }

    @PostMapping(value =  "/comment/{requestId}/submit")
    public String submitComment(Model model, @PathVariable String requestId, @ModelAttribute Comment comment) {
        List<Comment> updateCommentList = commentService.updateRequestWithComment(requestId, comment.getDescription());
        model.addAttribute("commentList", updateCommentList);
        model.addAttribute("comment", new Comment());
        return "ViewComment";
    }




}
