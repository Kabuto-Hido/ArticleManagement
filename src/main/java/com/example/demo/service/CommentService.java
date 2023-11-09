package com.example.demo.service;

import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.comment.CommentRequest;
import com.example.demo.dto.comment.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    CommentResponse save(CommentRequest model, String username);
    ListOutputResult getPagingByPostId(Long postId, String page, String limit);
    ListOutputResult getPagingByParentId(Long parentId, String page, String limit);
    List<CommentResponse> getByPostId(Long postId);
    List<CommentResponse> getByParentId(Long parentId);
}
