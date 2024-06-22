package com.elice.ggorangjirang.products.controller;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.products.dto.AddProductRequest;
import com.elice.ggorangjirang.products.dto.UpdateProductRequest;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.service.ProductService;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.elice.ggorangjirang.global.constant.GlobalConstants.*;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
@Slf4j
public class ProductAdminController {

    private final SubcategoryService subcategoryService;
    private final ProductService productService;
    private final S3Service s3Service;

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
    public String createProduct(@ModelAttribute AddProductRequest request,
                                @RequestPart(value = "productImageFile", required = false) MultipartFile productImageFile,
                                @RequestPart(value = "descriptionImageFile", required = false) MultipartFile descriptionImageFile) throws IOException {

        Product product = productService.createProduct(request, productImageFile, descriptionImageFile);
        log.info(PRODUCT_ADD_SUCCESS_LOG + product.getId());
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String showProductEditPage(@PathVariable("id") Long id, Model model) {
        List<Subcategory> subcategories = subcategoryService.findSubcategories();
        model.addAttribute("subcategories", subcategories);

        Product product = productService.findProduct(id);
        model.addAttribute("product", product);

        UpdateProductRequest request = new UpdateProductRequest();
        model.addAttribute("request", request);
        return "admin-products/product-edit";
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") Long id,
                              @ModelAttribute UpdateProductRequest request,
                              @RequestPart(value = "productImageFile", required = false) MultipartFile productImageFile,
                              @RequestPart(value = "descriptionImageFile", required = false) MultipartFile descriptionImageFile) throws IOException {

        Product updatedProduct = productService.updateProduct(id, request, productImageFile, descriptionImageFile);
        log.info(PRODUCT_EDIT_SUCCESS_LOG + id);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        log.warn(PRODUCT_DELETE_LOG + id);
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
