package com.elice.ggorangjirang.subcategories.controller;

import com.elice.ggorangjirang.subcategories.dto.SubcategoryResponse;
import com.elice.ggorangjirang.subcategories.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @GetMapping("/api/subcategories")
    public ResponseEntity<List<SubcategoryResponse>> getAllSubcategoriesWithCategoryName() {
        List<SubcategoryResponse> subcategories = subcategoryService.getSubcategoriesWithCategory();
        return ResponseEntity.ok(subcategories);
    }
}
