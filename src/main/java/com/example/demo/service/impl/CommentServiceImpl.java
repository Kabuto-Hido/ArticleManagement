package com.example.demo.service.impl;

import com.example.demo.dto.comment.CommentRequest;
import com.example.demo.dto.comment.CommentResponse;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentResponse save(CommentRequest model, String username) {
        Comment comment;
        if(model.getPostId() == null){
            throw new BadRequest("Please enter post!");
        }
        Post post = postRepository.findById(model.getPostId()).orElseThrow(
                () -> new NotFoundException("Not found post with id "+ model.getPostId()));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));

        if(model.getId() != null){
            comment = commentRepository.findById(model.getId()).orElseThrow(
                    () -> new NotFoundException("Not found comment with id "+ model.getId()));
            comment.setText(model.getText());
        }
        else {
            comment = CommentMapper.toEntity(model);
            comment.setPostId(post);
            comment.setUserId(user);

            if(model.getParentId() != null){
                Comment parentComment = commentRepository.findById(model.getParentId()).orElseThrow(
                        () -> new NotFoundException("Not found parent comment with id "+ model.getParentId()));
                comment.setParentId(parentComment);
            }
        }
        comment = commentRepository.save(comment);
        long amountChild = commentRepository.countByParentIdId(comment.getId());
        return CommentMapper.toDtoResponse(comment, user,amountChild);
    }

    @Override
    public List<CommentResponse> getByPostId(Long postId) {
        if(postId == null){
            throw new BadRequest("Please enter post!");
        }
        postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Not found post with id "+ postId));

        List<CommentResponse> commentResponses = new ArrayList<>();
        List<Comment> comments = commentRepository.findByPostIdIdAndParentIdIdNullOrderByCreatedDateDesc(postId);
        for (Comment comment : comments){
            long amountChild = commentRepository.countByParentIdId(comment.getId());
            commentResponses.add(CommentMapper.toDtoResponse(comment,comment.getUserId(),amountChild));
        }
        return commentResponses;
    }

    @Override
    public List<CommentResponse> getByParentId(Long parentId) {
        if(parentId == null){
            throw new BadRequest("Please enter parent id!");
        }
        commentRepository.findById(parentId).orElseThrow(
                () -> new NotFoundException("Not found parent comment with id "+ parentId));
        List<CommentResponse> commentResponses = new ArrayList<>();
        List<Comment> comments = commentRepository.findByParentIdIdOrderByCreatedDateDesc(parentId);
        for (Comment comment : comments){
            long amountChild = commentRepository.countByParentIdId(comment.getId());
            commentResponses.add(CommentMapper.toDtoResponse(comment,comment.getUserId(),amountChild));
        }
        return commentResponses;
    }
}
