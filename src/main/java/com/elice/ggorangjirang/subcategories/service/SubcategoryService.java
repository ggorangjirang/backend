package com.elice.ggorangjirang.subcategories.service;

import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.categories.repository.CategoryRepository;
import com.elice.ggorangjirang.subcategories.dto.AddSubcategoryRequest;
import com.elice.ggorangjirang.subcategories.dto.SubcategoryResponse;
import com.elice.ggorangjirang.subcategories.dto.UpdateSubcategoryRequest;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public List<Subcategory> findSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Subcategory findSubcategory(Long id) {
        Subcategory foundSubcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        return foundSubcategory;
    }

    @Transactional
    public Subcategory createSubcategory(AddSubcategoryRequest request) {
        return subcategoryRepository.save(request.toEntity());
    }

    @Transactional
    public Subcategory updateSubcategory(Long id, UpdateSubcategoryRequest request) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.getCategoryId()));

        subcategory.update(request.getSubcategoryName(), category, request.getCategoryId());
        return subcategory;
    }

    @Transactional
    public void deleteSubcategory(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        subcategoryRepository.deleteById(subcategory.getId());
    }

    public List<SubcategoryResponse> getSubcategoriesWithCategoryName() {
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        return subcategories.stream()
                .map(subcategory -> new SubcategoryResponse(
                        subcategory.getSubcategoryName(),
                        subcategory.getCategory().getCategoryName()
                ))
                .collect(Collectors.toList());
    }
}
