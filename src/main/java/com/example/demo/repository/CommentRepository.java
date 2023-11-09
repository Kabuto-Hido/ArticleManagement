package com.example.demo.repository;

import com.example.demo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdIdAndParentIdIdNullOrderByCreatedDateDesc(long postId);
    Page<Comment> findByPostIdIdAndParentIdIdNull(long postId, Pageable pageable);
    List<Comment> findByParentIdIdOrderByCreatedDateDesc(long parentId);
    Page<Comment> findByParentIdId(long parentId, Pageable pageable);
    long countByParentIdId(long commentId);
}
