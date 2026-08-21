package com.nkydev.controller;

import com.nkydev.entity.Category;
import com.nkydev.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){return categoryService.getCategoryById(id);}

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Integer id, @RequestBody Category categoryDetail){
        categoryService.updateCategory(id, categoryDetail);
    }

    @DeleteMapping("/{id}")
    public void deteleCategory (@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }
}