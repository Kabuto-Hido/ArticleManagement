package com.example.demo.repository;

import com.example.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdIdAndParentIdIdNullOrderByCreatedDateDesc(long postId);
    List<Comment> findByParentIdIdOrderByCreatedDateDesc(long parentId);
    long countByParentIdId(long commentId);
}
