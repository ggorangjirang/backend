package com.elice.ggorangjirang.products.controller;

import com.elice.ggorangjirang.products.dto.ListProductResponse;
import com.elice.ggorangjirang.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/limited-sale/random")
    public ResponseEntity<List<ListProductResponse>> getEightLimitedSaleProducts() {
        List<ListProductResponse> products = productService.getEightLimitedSaleProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/limited-sale")
    public ResponseEntity<Page<ListProductResponse>> getAllLimitedSaleProducts(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getAllLimitedSaleProducts(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/best-selling/random")
    public ResponseEntity<List<ListProductResponse>> getEightBestSellingProducts() {
        List<ListProductResponse> products = productService.getEightBestSellingProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/best-selling")
    public ResponseEntity<Page<ListProductResponse>> getAllBestSellingProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getAllBestSellingProducts(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-order-count-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByOrderCountDesc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByOrderCountDesc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-created-at-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByCreatedAtDesc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByCreatedAtDesc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-price-asc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByPriceAsc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByPriceAsc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-price-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByPriceDesc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByPriceDesc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-view-count-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByViewCountDesc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByViewCountDesc(page, size);
        return ResponseEntity.ok(products);
    }
}
