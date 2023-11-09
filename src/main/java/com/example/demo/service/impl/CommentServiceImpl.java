package com.example.demo.service.impl;

import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.ListOutputResult;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        if (model.getPostId() == null) {
            throw new BadRequest("Please enter post!");
        }
        Post post = postRepository.findById(model.getPostId()).orElseThrow(
                () -> new NotFoundException("Not found post with id " + model.getPostId()));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));

        if (model.getId() != null) {
            comment = commentRepository.findById(model.getId()).orElseThrow(
                    () -> new NotFoundException("Not found comment with id " + model.getId()));
            comment.setText(model.getText());
        } else {
            comment = CommentMapper.toEntity(model);
            comment.setPostId(post);
            comment.setUserId(user);

            if (model.getParentId() != null) {
                Comment parentComment = commentRepository.findById(model.getParentId()).orElseThrow(
                        () -> new NotFoundException("Not found parent comment with id " + model.getParentId()));
                comment.setParentId(parentComment);
            }
        }
        comment = commentRepository.save(comment);
        long amountChild = commentRepository.countByParentIdId(comment.getId());
        return CommentMapper.toDtoResponse(comment, user, amountChild);
    }

    @Override
    public ListOutputResult getPagingByPostId(Long postId, String page, String limit) {
        checkExistComment(postId);

        Pageable pageable = preparePaging(page, limit);
        Page<Comment> comments =
                commentRepository.findByPostIdIdAndParentIdIdNull(postId, pageable);

        return resultPaging(comments);
    }

    @Override
    public ListOutputResult getPagingByParentId(Long parentId, String page, String limit) {
        checkExistComment(parentId);

        Pageable pageable = preparePaging(page, limit);
        Page<Comment> comments =
                commentRepository.findByParentIdId(parentId, pageable);

        return resultPaging(comments);
    }

    @Override
    public List<CommentResponse> getByPostId(Long postId) {
        checkExistComment(postId);

        List<Comment> comments = commentRepository.findByPostIdIdAndParentIdIdNullOrderByCreatedDateDesc(postId);
        return convertToCommentResponse(comments);
    }

    @Override
    public List<CommentResponse> getByParentId(Long parentId) {
        checkExistComment(parentId);

        List<Comment> comments = commentRepository.findByParentIdIdOrderByCreatedDateDesc(parentId);
        return convertToCommentResponse(comments);
    }

    public void checkExistComment(Long id){
        if (id == null) {
            throw new BadRequest("Please enter id!");
        }
        commentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found comment with id " + id));
    }

    public List<CommentResponse> convertToCommentResponse(List<Comment> commentList){
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : commentList) {
            long amountChild = commentRepository.countByParentIdId(comment.getId());
            commentResponses.add(CommentMapper.toDtoResponse(comment, comment.getUserId(), amountChild));
        }
        return commentResponses;
    }

    private Boolean isNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean isValidNumber(String num) {
        return num != null && !num.isEmpty() && isNumber(num) && Long.parseLong(num) >= 0;
    }

    public Pageable preparePaging(String page, String limit) {
        limit = isValidNumber(limit) ? limit : GlobalVariable.DEFAULT_LIMIT_COMMENT;
        page = isValidNumber(page) ? page : GlobalVariable.DEFAULT_PAGE;
        return PageRequest.of((Integer.parseInt(page) - 1), Integer.parseInt(limit),
                Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    public ListOutputResult resultPaging(Page<Comment> comments) {
        List<CommentResponse> commentResponses = convertToCommentResponse(comments.getContent());
        ListOutputResult result = new ListOutputResult(0, 0, null, null, new ArrayList<>());
        if (!comments.isEmpty()) {
            result.setResult(commentResponses);
            result.setTotalPage(comments.getTotalPages());
            result.setItemsNumber(comments.getTotalElements());

            if (comments.hasNext()) {
                result.setNextPage((long) comments.nextPageable().getPageNumber() + 1);
            }
            if (comments.hasPrevious()) {
                result.setPreviousPage((long) comments.previousPageable().getPageNumber() + 1);
            }
        }
        return result;
    }
}
