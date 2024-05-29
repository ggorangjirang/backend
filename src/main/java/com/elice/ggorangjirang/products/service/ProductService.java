package com.elice.ggorangjirang.products.service;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.products.dto.AddProductRequest;
import com.elice.ggorangjirang.products.dto.DetailProductResponse;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final S3Service s3Service;

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findProduct(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        return foundProduct;
    }

    @Transactional
    public Product createProduct(
            AddProductRequest request,
            MultipartFile productImageFile,
            MultipartFile descriptionImageFile) throws IOException {

        String productImageUrl = null;
        if(productImageFile != null && !productImageFile.isEmpty()) {
            productImageUrl = s3Service.uploadProductImage(productImageFile);
        }

        String descriptionImageUrl = null;
        if(descriptionImageFile != null && !descriptionImageFile.isEmpty()) {
            descriptionImageUrl = s3Service.uploadDescriptionImage(descriptionImageFile);
        }

        request.setProductImageUrl(productImageUrl);
        request.setDescriptionImageUrl(descriptionImageUrl);

        return productRepository.save(request.toEntity());
    }

    @Transactional
    public Product updateProduct(Long id, UpdateProductRequest request,
                                 MultipartFile productImageFile,
                                 MultipartFile descriptionImageFile) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        Subcategory subcategory = subcategoryRepository.findById(request.getSubcategoryId())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        String oldProductImageUrl = product.getProductImageUrl();
        String newProductImageUrl = oldProductImageUrl;

        if (productImageFile != null && !productImageFile.isEmpty()) {
            newProductImageUrl = s3Service.uploadProductImage(productImageFile);
            if (oldProductImageUrl != null) {
                s3Service.deleteFile(oldProductImageUrl);
            }
        }

        String oldDescriptionImageUrl = product.getDescriptionImageUrl();
        String newDescriptionImageUrl = oldDescriptionImageUrl;

        if (descriptionImageFile != null && !descriptionImageFile.isEmpty()) {
            newDescriptionImageUrl = s3Service.uploadDescriptionImage(descriptionImageFile);
            if (oldDescriptionImageUrl != null) {
                s3Service.deleteFile(oldDescriptionImageUrl);
            }
        }

        request.setProductImageUrl(newProductImageUrl);
        request.setDescriptionImageUrl(newDescriptionImageUrl);

        product.update(
                request.getName(), request.getDescription(), request.getPrice(), request.getExpirationDate(),
                request.getDiscountRate(), request.getProductImageUrl(), request.getDescriptionImageUrl(), request.getStock(), request.getSubcategoryId(),
                subcategory);

        return product;
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        productRepository.delete(product);
    }

    private int calculateDiscountedPrice(int price, float discountRate) {
        int discountedPrice = Math.round(price * (1 - discountRate / 100));
        return (discountedPrice + 5) / 10 * 10;
    }

    private ListProductResponse convertToListProductResponse(Product product) {
        return new ListProductResponse(
                product.getId(),
                product.getName(),
                product.getDiscountRate(),
                product.getPrice(),
                calculateDiscountedPrice(product.getPrice(), product.getDiscountRate()),
                product.getProductImageUrl(),
                product.getStock());
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

    public Page<ListProductResponse> getProductsByOrderCountDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByOrderCountDesc(pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public Page<ListProductResponse> getProductsByCreatedAtDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByCreatedAtDesc(pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public Page<ListProductResponse> getProductsByPriceAsc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByPriceAsc(pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public Page<ListProductResponse> getProductsByPriceDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByPriceDesc(pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public Page<ListProductResponse> getProductsByViewCountDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByViewCountDesc(pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public Page<ListProductResponse> getProductsByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByCategory(categoryId, pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public Page<ListProductResponse> getProductsBySubcategory(Long subcategoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findBySubcategory(subcategoryId, pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public Page<ListProductResponse> getProductsByKeyword(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByKeyword(keyword, pageable);
        return productPage.map(this::convertToListProductResponse);
    }

    public DetailProductResponse getProductDetail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

        product.addViewCount();
        productRepository.save(product);

        String subcategoryName = product.getSubcategory() != null ?
                product.getSubcategory().getSubcategoryName() :
                null;

        String categoryName = product.getSubcategory() != null && product.getSubcategory().getCategory() != null ?
                product.getSubcategory().getCategory().getCategoryName() :
                null;

        return new DetailProductResponse(
                product.getId(),
                product.getName(),
                product.getDiscountRate(),
                product.getPrice(),
                calculateDiscountedPrice(product.getPrice(), product.getDiscountRate()),
                product.getProductImageUrl(),
                product.getStock(),
                product.getExpirationDate(),
                subcategoryName,
                categoryName,
                product.getDescription(),
                product.getDescriptionImageUrl(),
                product.isSoldOut());
    }
}
