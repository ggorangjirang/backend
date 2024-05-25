package com.elice.ggorangjirang.products.service;

import com.elice.ggorangjirang.products.dto.AddProductRequest;
import com.elice.ggorangjirang.products.dto.ListProductResponse;
import com.elice.ggorangjirang.products.dto.UpdateProductRequest;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findProduct(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        return foundProduct;
    }

    @Transactional
    public Product createProduct(AddProductRequest request) {
        return productRepository.save(request.toEntity());
    }

    @Transactional
    public Product updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        Subcategory subcategory = subcategoryRepository.findById(request.getSubcategoryId())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        product.update(
                request.getName(), request.getDescription(), request.getPrice(), request.getExpirationDate(),
                request.getDiscountRate(), request.getImageUrl(), request.getStock(), request.getSubcategoryId(),
                subcategory);

        return product;
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        productRepository.delete(product);
    }

    private ListProductResponse convertToListProductResponse(Product product) {
        return new ListProductResponse(product.getName(), product.getDiscountRate(), product.getPrice(),
                product.getImageUrl(), product.getStock());
    }

    public List<ListProductResponse> getEightLimitedSaleProducts() {
        List<Product> products = productRepository.findLimitedSaleProducts();
        Collections.shuffle(products);
        return products.stream()
                .limit(8)
                .map(this::convertToListProductResponse)
                .collect(Collectors.toList());
    }

    public Page<ListProductResponse> getAllLimitedSaleProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findLimitedSaleProducts(pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public List<ListProductResponse> getEightBestSellingProducts() {
        List<Product> products = productRepository.findBestSellingProducts();
        Collections.shuffle(products);
        return products.stream()
                .limit(8)
                .map(this::convertToListProductResponse)
                .collect(Collectors.toList());
    }

    public Page<ListProductResponse> getAllBestSellingProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findBestSellingProducts(pageable);
        return productPage.map(this::convertToListProductResponse);
    }

}
