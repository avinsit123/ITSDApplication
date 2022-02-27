package com.io.request.services;


import com.io.request.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllCommentsForUser(String userId);

}
