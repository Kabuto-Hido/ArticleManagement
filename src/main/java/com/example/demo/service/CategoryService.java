package com.example.demo.service;

import com.example.demo.dto.category.CategoryDTO;
import com.example.demo.dto.ListOutputResult;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryDTO save (CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(long id);
    ListOutputResult findAll(String page, String limit);
    ListOutputResult searchCategory(String keyword, String page, String limit);
}
