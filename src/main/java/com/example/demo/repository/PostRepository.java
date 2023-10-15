package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PostRepository extends JpaRepository<Post,Long>, JpaSpecificationExecutor<Post> {
    Page<Post> findAllByStatus(String status, Pageable pageable);
    Page<Post> findAllByUserIdId(long userId, Pageable pageable);
    Page<Post> findAllByCategoryIdId(long categoryId, Pageable pageable);
    Page<Post> findAllByStatusAndUserIdId(String status, long userId, Pageable pageable);
    Page<Post> findAllByStatusAndCategoryIdId(String status, long categoryId, Pageable pageable);
}
