package com.elice.ggorangjirang.products.controller;

import com.elice.ggorangjirang.products.dto.DetailProductResponse;
import com.elice.ggorangjirang.products.dto.ListProductResponse;
import com.elice.ggorangjirang.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                @RequestParam(name = "page", defaultValue = "0") int page,
                @RequestParam(name = "size", defaultValue = "16") int size) {
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
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getAllBestSellingProducts(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-order-count-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByOrderCountDesc(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByOrderCountDesc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-created-at-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByCreatedAtDesc(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByCreatedAtDesc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-price")
    public ResponseEntity<Page<ListProductResponse>> getProductsByPriceAsc(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size,
            @RequestParam(name = "sort", defaultValue = "asc") String sort) {

        if (sort.equals("desc")) {
            Page<ListProductResponse> products = productService.getProductsByPriceDesc(page, size);
            return ResponseEntity.ok(products);
        } else if (sort.equals("asc")) {
            Page<ListProductResponse> products = productService.getProductsByPriceAsc(page, size);
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/by-price-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByPriceDesc(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByPriceDesc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-view-count-desc")
    public ResponseEntity<Page<ListProductResponse>> getProductsByViewCountDesc(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByViewCountDesc(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ListProductResponse>> getProductsByCategory(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByCategory(categoryId, page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/subcategory/{subcategoryId}")
    public ResponseEntity<Page<ListProductResponse>> getProductsBySubcategory(
            @PathVariable("subcategoryId") Long subcategoryId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsBySubcategory(subcategoryId, page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ListProductResponse>> searchProductsByKeyword(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "16") int size) {
        Page<ListProductResponse> products = productService.getProductsByKeyword(keyword, page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailProductResponse> getProductDetail(@PathVariable("id") Long id) {
        DetailProductResponse productDetail = productService.getProductDetail(id);
        return ResponseEntity.ok(productDetail);
    }
}
