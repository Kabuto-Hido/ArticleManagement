package com.example.demo.mapper;

import com.example.demo.dto.comment.CommentRequest;
import com.example.demo.dto.comment.CommentResponse;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;

public class CommentMapper {
    public static CommentResponse toDtoResponse(Comment comment, User user, long amountChild){
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setText(comment.getText());
        response.setAmountChild(amountChild);
        if(comment.getParentId() != null){
            response.setParentId(comment.getParentId().getId());
        }
        response.setPostId(comment.getPostId().getId());
        response.setCommenter(user.getUsername());
        response.setAvatar(user.getAvatar());
        response.setCreatedDate(comment.getCreatedDate());
        response.setModifiedDate(comment.getModifiedDate());
        return response;
    }
    public static Comment toEntity(CommentRequest request){
        Comment comment = new Comment();
        comment.setText(request.getText());
        return comment;
    }
}
