package com.example.demo.repository;

import com.example.demo.entity.TypeAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TypeAccountRepository extends JpaRepository<TypeAccount, Long> {
    TypeAccount findOneByTypename(String name);
}
