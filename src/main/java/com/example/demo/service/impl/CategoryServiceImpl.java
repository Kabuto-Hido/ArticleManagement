package com.example.demo.service.impl;

import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.category.CategoryDTO;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.entity.Category;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private Boolean isNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean isValidNumber(String num) {
        return num != null && !num.equals("") && isNumber(num) && Long.parseLong(num) >= 0;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category;
        if(categoryRepository.findOneByName(categoryDTO.getName()) != null){
            throw new BadRequest("This name have been used!!");
        }

        if(categoryDTO.getId() != null){
            category = categoryRepository.findById(categoryDTO.getId()).orElseThrow(()
            -> new BadRequest("Not found category with id " + categoryDTO.getId()));
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
        }
        else {
            category = CategoryMapper.toEntity(categoryDTO);
        }
        category = categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->
                new NotFoundException("Category not found!"));
        return CategoryMapper.toDto(category);
    }

    @Override
    public ListOutputResult findAll(String page, String limit) {
        limit = (!isValidNumber(limit)) ? GlobalVariable.DEFAULT_LIMIT_USER : limit;
        page = (!isValidNumber(page)) ? GlobalVariable.DEFAULT_PAGE : page;

        Pageable pages = PageRequest.of((Integer.parseInt(page) - 1),Integer.parseInt(limit),
                Sort.by(Sort.Direction.DESC, "createdDate"));

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        Page<Category> categories = categoryRepository.findAll(pages);
        for(Category category : categories){
            categoryDTOS.add(CategoryMapper.toDto(category));
        }

        if (categories.isEmpty()) {
            return new ListOutputResult(0,0, new ArrayList<>());
        }

        ListOutputResult result = new ListOutputResult();
        result.setList(categoryDTOS);
        result.setTotalPage(categories.getTotalPages());
        result.setItemsNumber(categories.getTotalElements());
        return result;
    }

    @Override
    public ListOutputResult searchCategory(String keyword, String page, String limit) {
        if(keyword == null){
            throw new BadRequest("Please enter valid "+ keyword);
        }

        limit = (!isValidNumber(limit)) ? GlobalVariable.DEFAULT_LIMIT_SEARCH : limit;
        page = (!isValidNumber(page)) ? GlobalVariable.DEFAULT_PAGE : page;
        Pageable pages = PageRequest.of((Integer.parseInt(page) - 1),Integer.parseInt(limit),
                Sort.by(Sort.Direction.DESC, "createdDate"));

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        Page<Category> categories = categoryRepository.search(keyword, pages);

        for(Category category : categories){
            categoryDTOS.add(CategoryMapper.toDto(category));
        }

        if (categories.isEmpty()) {
            return new ListOutputResult(0,0, new ArrayList<>());
        }

        ListOutputResult result = new ListOutputResult();
        result.setList(categoryDTOS);
        result.setTotalPage(categories.getTotalPages());
        result.setItemsNumber(categories.getTotalElements());
        return result;
    }
}
