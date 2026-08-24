package com.nkydev.service;

import com.nkydev.dto.category.CategoryRequestDTO;
import com.nkydev.dto.category.CategoryResponseDTO;
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

    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        Category category = new Category();
        category.setName(request.name());

        Category savedCategory = categoryRepository.save(category);
        return mapToResponseDTO(savedCategory);
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("category not found with ID: " + id));
        return mapToResponseDTO(category);
    }

    @Transactional
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("category not found with ID: " + id));

        category.setName(request.name());

        return mapToResponseDTO(category);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDTO mapToResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}