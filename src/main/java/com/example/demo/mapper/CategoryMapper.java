package com.example.demo.mapper;

import com.example.demo.dto.category.CategoryDTO;
import com.example.demo.entity.Category;

public class CategoryMapper {
    public static CategoryDTO toDto(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setCreatedDate(category.getCreatedDate());
        categoryDTO.setModifiedDate(category.getModifiedDate());
        return categoryDTO;
    }

    public static Category toEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
