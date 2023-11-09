package com.example.demo.controller;

import com.example.demo.dto.category.CategoryDTO;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/category/createNew")
    public ResponseEntity<?> createNewCategory(@RequestBody CategoryDTO model){
        return ResponseEntity.ok(categoryService.save(model));
    }

    @PutMapping("/admin/category/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryDTO model, @PathVariable long id){
        model.setId(id);
        return ResponseEntity.ok(categoryService.save(model));
    }

    @GetMapping("/category/get-all")
    public ResponseEntity<?> getListCategory(@RequestParam(required = false) String page,
                                         @RequestParam(required = false) String limit){
        ListOutputResult result = categoryService.findAll(page,limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/category/search")
    public ResponseEntity<?> searchCategory(@RequestParam String keyword,
                                        @RequestParam(required = false) String page,
                                        @RequestParam(required = false) String limit){
        ListOutputResult result = categoryService.searchCategory(keyword,page,limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}
