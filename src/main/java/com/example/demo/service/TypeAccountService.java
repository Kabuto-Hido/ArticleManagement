package com.example.demo.service;

import com.example.demo.entity.TypeAccount;
import org.springframework.stereotype.Service;

@Service
public interface TypeAccountService {
    TypeAccount getTypeByName(String name);
}
