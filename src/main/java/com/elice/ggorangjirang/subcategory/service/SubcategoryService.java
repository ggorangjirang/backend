package com.elice.ggorangjirang.subcategory.service;

import com.elice.ggorangjirang.subcategory.dto.AddSubcategoryRequest;
import com.elice.ggorangjirang.subcategory.dto.UpdateSubcategoryRequest;
import com.elice.ggorangjirang.subcategory.entity.Subcategory;
import com.elice.ggorangjirang.subcategory.repository.SubcategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;

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

        subcategory.update(request.getSubcategoryName());

        return subcategory;
    }

    @Transactional
    public void deleteSubcategory(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        subcategoryRepository.deleteById(subcategory.getId());
    }
}
