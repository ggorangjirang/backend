package com.elice.ggorangjirang.products.controller;

import com.elice.ggorangjirang.products.service.ProductService;
import com.elice.ggorangjirang.subcategories.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final SubcategoryService subcategoryService;
    private final ProductService productService;


}
