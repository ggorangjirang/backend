package com.elice.ggorangjirang.subcategories.controller;

import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.categories.service.CategoryService;
import com.elice.ggorangjirang.subcategories.dto.AddSubcategoryRequest;
import com.elice.ggorangjirang.subcategories.dto.UpdateSubcategoryRequest;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/subcategories")
@RequiredArgsConstructor
public class SubcategoryAdminController {

    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;

    @GetMapping
    public String getSubcategories(Model model) {
        List<Category> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);
        List<Subcategory> subcategories = subcategoryService.findSubcategories();
        model.addAttribute("subcategories", subcategories);

        return "admin-category/subcategories";
    }

    @GetMapping("/add")
    public String showSubcategoryAddPage(Model model) {
        List<Category> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("request", new AddSubcategoryRequest());
        return "admin-category/subcategory-add";
    }

    @PostMapping("/add")
    public String createSubcategory(@ModelAttribute AddSubcategoryRequest request) {
        subcategoryService.createSubcategory(request);
        return "redirect:/admin/subcategories";
    }

    @GetMapping("/{id}/edit")
    public String showSubcategoryEditPage(@PathVariable Long id, Model model) {
        List<Category> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);

        Subcategory subcategory = subcategoryService.findSubcategory(id);
        model.addAttribute("subcategory", subcategory);

        UpdateSubcategoryRequest request = new UpdateSubcategoryRequest();
        model.addAttribute("request", request);
        return "admin-category/subcategory-edit";
    }

    @PostMapping("/{id}/edit")
    public String editSubcategory(@PathVariable Long id, @ModelAttribute UpdateSubcategoryRequest request) {
        Subcategory updatedSubcategory = subcategoryService.updateSubcategory(id, request);
        return "redirect:/admin/subcategories";
    }

    @DeleteMapping("/{id}")
    public String deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
        return "redirect:/admin/subcategories";
    }
}
