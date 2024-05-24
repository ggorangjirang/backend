package com.elice.ggorangjirang.categories.controller;

import com.elice.ggorangjirang.categories.dto.AddCategoryRequest;
import com.elice.ggorangjirang.categories.dto.UpdateCategoryRequest;
import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String getCategories(Model model) {
        List<Category> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);

        return "admin-category/categories";
    }

    @GetMapping("/add")
    public String showCategoryAddPage(Model model) {
        return "admin-category/category-add";
    }

    @PostMapping("/add")
    public String createCategory(@ModelAttribute AddCategoryRequest request) {
        categoryService.createCategory(request);
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/edit")
    public String showCategoryEditPage(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategory(id);
        model.addAttribute("category", category);

        return "admin-category/category-edit";
    }

    @PostMapping("/{id}/edit")
    public String editCategory(@PathVariable Long id, @ModelAttribute UpdateCategoryRequest request) {
        Category updatedCategory = categoryService.updateCategory(id, request);
        return "redirect:/admin/categories";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}
