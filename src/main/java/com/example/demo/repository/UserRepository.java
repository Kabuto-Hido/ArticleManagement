package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u WHERE u.fullName LIKE %?1% OR u.username LIKE %?1%")
    Page<User> search(String keyword, Pageable pageable);
    User findFirstByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String email);
    List<User> findByfullNameContaining(String name);
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM User WHERE name = :name")
//    Integer deleteUserByName(String name);
}
