package com.elice.ggorangjirang.categories.controller;

import com.elice.ggorangjirang.categories.dto.CategoryResponse;
import com.elice.ggorangjirang.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

}
