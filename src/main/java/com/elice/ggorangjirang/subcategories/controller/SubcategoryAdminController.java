package com.elice.ggorangjirang.subcategories.controller;

import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.categories.service.CategoryService;
import com.elice.ggorangjirang.subcategories.dto.AddSubcategoryRequest;
import com.elice.ggorangjirang.subcategories.dto.UpdateSubcategoryRequest;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elice.ggorangjirang.global.constant.GlobalConstants.*;

@Controller
@RequestMapping("/admin/subcategories")
@RequiredArgsConstructor
@Slf4j
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
        Subcategory subcategory = subcategoryService.createSubcategory(request);
        log.info(SUBCATEGORY_ADD_SUCCESS + subcategory.getSubcategoryName());
        return "redirect:/admin/subcategories";
    }

    @GetMapping("/{id}/edit")
    public String showSubcategoryEditPage(@PathVariable("id") Long id, Model model) {
        List<Category> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);

        Subcategory subcategory = subcategoryService.findSubcategory(id);
        model.addAttribute("subcategory", subcategory);

        UpdateSubcategoryRequest request = new UpdateSubcategoryRequest();
        model.addAttribute("request", request);
        return "admin-category/subcategory-edit";
    }

    @PostMapping("/{id}/edit")
    public String editSubcategory(@PathVariable("id") Long id, @ModelAttribute UpdateSubcategoryRequest request) {
        String subcategoryNameBeforeChanged = subcategoryService.findSubcategory(id).getSubcategoryName();
        Subcategory updatedSubcategory = subcategoryService.updateSubcategory(id, request);
        String subcategoryNameAfterChanged = updatedSubcategory.getSubcategoryName();

        log.info(SUBCATEGORY_EDIT_SUCCESS + subcategoryNameBeforeChanged + " --> " + subcategoryNameAfterChanged);
        return "redirect:/admin/subcategories";
    }

    @DeleteMapping("/{id}")
    public String deleteSubcategory(@PathVariable("id") Long id) {
        Subcategory deletingSubcategory = subcategoryService.findSubcategory(id);
        log.warn(SUBCATEGORY_DELETE + deletingSubcategory.getSubcategoryName());
        subcategoryService.deleteSubcategory(id);
        return "redirect:/admin/subcategories";
    }
}
