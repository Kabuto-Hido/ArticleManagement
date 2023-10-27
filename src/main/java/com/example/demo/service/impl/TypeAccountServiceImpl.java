package com.example.demo.service.impl;

import com.example.demo.entity.TypeAccount;
import com.example.demo.repository.TypeAccountRepository;
import com.example.demo.service.TypeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeAccountServiceImpl implements TypeAccountService {
    @Autowired
    private TypeAccountRepository typeAccountRepository;
    @Override
    public TypeAccount getTypeByName(String name) {
        return typeAccountRepository.findOneByTypename(name);
    }
}
