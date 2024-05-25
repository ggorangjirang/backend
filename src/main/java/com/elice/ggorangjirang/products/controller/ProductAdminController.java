package com.elice.ggorangjirang.products.controller;

import com.elice.ggorangjirang.products.dto.AddProductRequest;
import com.elice.ggorangjirang.products.dto.UpdateProductRequest;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.service.ProductService;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final SubcategoryService subcategoryService;
    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        List<Subcategory> subcategories = subcategoryService.findSubcategories();
        model.addAttribute("subcategories", subcategories);

        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);

        return "admin-products/products";
    }

    @GetMapping("/add")
    public String showProductAddPage(Model model) {
        List<Subcategory> subcategories = subcategoryService.findSubcategories();
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("request", new AddProductRequest());
        return "admin-products/product-add";
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute AddProductRequest request) {
        productService.createProduct(request);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String showProductEditPage(@PathVariable Long id, Model model) {
        List<Subcategory> subcategories = subcategoryService.findSubcategories();
        model.addAttribute("subcategories", subcategories);

        Product product = productService.findProduct(id);
        model.addAttribute("product", product);

        UpdateProductRequest request = new UpdateProductRequest();
        model.addAttribute("request", request);
        return "admin-products/product-edit";
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, @ModelAttribute UpdateProductRequest request) {
        Product updatedProduct = productService.updateProduct(id, request);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}