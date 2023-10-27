package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = ?2 WHERE o.id = ?1")
    void changeStatus(long id, String status);
}
