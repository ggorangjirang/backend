package com.elice.ggorangjirang.categories.controller;

import com.elice.ggorangjirang.categories.dto.AddCategoryRequest;
import com.elice.ggorangjirang.categories.dto.UpdateCategoryRequest;
import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elice.ggorangjirang.global.constant.GlobalConstants.*;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryAdminController {

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
        Category category = categoryService.createCategory(request);
        log.info(CATEGORY_ADD_SUCCESS_LOG + category.getCategoryName());
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/edit")
    public String showCategoryEditPage(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findCategory(id);
        model.addAttribute("category", category);

        return "admin-category/category-edit";
    }

    @PostMapping("/{id}/edit")
    public String editCategory(@PathVariable("id") Long id, @ModelAttribute UpdateCategoryRequest request) {
        String categoryNameBeforeChanged = categoryService.findCategory(id).getCategoryName();
        Category updatedCategory = categoryService.updateCategory(id, request);
        String categoryNameAfterChanged = updatedCategory.getCategoryName();

        log.info(CATEGORY_EDIT_SUCCESS_LOG + categoryNameBeforeChanged + " --> " + categoryNameAfterChanged);
        return "redirect:/admin/categories";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category deletingCategory = categoryService.findCategory(id);
        log.warn(CATEGORY_DELETE_LOG + deletingCategory.getCategoryName());
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}
