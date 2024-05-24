package com.elice.ggorangjirang.products.service;

import com.elice.ggorangjirang.products.dto.AddProductRequest;
import com.elice.ggorangjirang.products.dto.UpdateProductRequest;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        productRepository.deleteById(product.getId());
    }
}
