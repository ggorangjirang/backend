package com.elice.ggorangjirang.categories.service;

import com.elice.ggorangjirang.categories.dto.AddCategoryRequest;
import com.elice.ggorangjirang.categories.dto.UpdateCategoryRequest;
import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.categories.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategory(Long id) {
        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        return foundCategory;
    }

    @Transactional
    public Category createCategory(AddCategoryRequest request) {
        return categoryRepository.save(request.toEntity());
    }

    @Transactional
    public Category updateCategory(Long id, UpdateCategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        category.update(request.getCategoryName());

        return category;
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        categoryRepository.deleteById(category.getId());
    }
}
