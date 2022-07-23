package com.io.request.services;


import com.io.request.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllCommentsForRequest(String requestId);

    List<Comment> updateRequestWithComment(String requestId, String commentLine);

}
