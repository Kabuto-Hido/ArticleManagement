package com.example.demo.repository;

import com.example.demo.config.FeelingType;
import com.example.demo.entity.Feeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface FeelingRepository extends JpaRepository<Feeling, Long> {
    long countByPostIdIdAndType(long postId, FeelingType type);
    Feeling findOneByUserIdIdAndPostIdId(long userId, long postId);
}
