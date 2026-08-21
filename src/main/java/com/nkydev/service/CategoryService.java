package com.nkydev.service;

import com.nkydev.entity.Category;
import com.nkydev.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id){
        return categoryRepository
                .findById(id).orElseThrow(() -> new IllegalStateException("category not found with ID: " + id));
    }

    @Transactional
    public void updateCategory(Integer id, Category categoryDetail){
        Category category = categoryRepository
                .findById(id).orElseThrow(() -> new IllegalStateException("category not found with ID: " + id));

        category.setName(categoryDetail.getName());
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}