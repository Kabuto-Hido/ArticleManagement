package com.example.demo.service.impl;

import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.category.CategoryDTO;
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
        return num != null && !num.isEmpty() && isNumber(num) && Long.parseLong(num) >= 0;
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

    public Pageable preparePaging(String page, String limit){
        limit = isValidNumber(limit) ? limit : GlobalVariable.DEFAULT_LIMIT_USER;
        page = isValidNumber(page) ? page : GlobalVariable.DEFAULT_PAGE;
        return PageRequest.of((Integer.parseInt(page) - 1),Integer.parseInt(limit),
                Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    public ListOutputResult resultPaging(Page<Category> categories){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category : categories){
            categoryDTOS.add(CategoryMapper.toDto(category));
        }

        ListOutputResult result = new ListOutputResult(0,0,null,null, new ArrayList<>());
        if(!categories.isEmpty()) {
            long ItemsNumber = categories.getTotalElements();
            long totalPage = categories.getTotalPages();
            result.setList(categoryDTOS);
            result.setTotalPage(totalPage);
            result.setItemsNumber(ItemsNumber);

            if (categories.hasNext()) {
                result.setNextPage((long) categories.nextPageable().getPageNumber() + 1);
            }
            if (categories.hasPrevious()) {
                result.setPreviousPage((long) categories.previousPageable().getPageNumber() + 1);
            }
        }
        return result;
    }
    @Override
    public ListOutputResult findAll(String page, String limit) {
        Pageable pages = preparePaging(page,limit);

        Page<Category> categories = categoryRepository.findAll(pages);

        return resultPaging(categories);
    }

    @Override
    public ListOutputResult searchCategory(String keyword, String page, String limit) {
        if(keyword == null){
            throw new BadRequest("Please enter valid keyword!");
        }

        Pageable pages = preparePaging(page,limit);

        Page<Category> categories = categoryRepository.search(keyword, pages);

        return resultPaging(categories);
    }
}
