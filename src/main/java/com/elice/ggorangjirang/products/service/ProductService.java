package com.elice.ggorangjirang.products.service;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.discord.DiscordWebhook;
import com.elice.ggorangjirang.products.dto.AddProductRequest;
import com.elice.ggorangjirang.products.dto.DetailProductResponse;
import com.elice.ggorangjirang.products.dto.ListProductResponse;
import com.elice.ggorangjirang.products.dto.UpdateProductRequest;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.exception.InvalidProductDataException;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.products.exception.SubcategoryNotFoundException;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final S3Service s3Service;
    private final DiscordWebhook discordWebhook;

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found with id: ";
    private static final String INVALID_PRODUCT_MESSAGE = "Price or Stock cannot be minus value.";
    private static final String SUBCATEGORY_NOT_FOUND_MESSAGE = "Subcategory not found with id: ";
    private static final String NEW_PRODUCT_NOTICE = "New Product has created: ";
    private static final String PRODUCT_CHANGED_NOTICE = "Product has changed. Check the details in logs: ";
    private static final String PRODUCT_DELETED_NOTICE = "Product has deleted: ";

    // Spring MVC 방식 관리자 페이지용
    public List<Product> findProducts() {
        return productRepository.findAll();
    }


    // Spring MVC 방식 관리자 페이지용
    public Product findProduct(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id));

        return foundProduct;
    }

    @Transactional
    public Product createProduct(
            AddProductRequest request,
            MultipartFile productImageFile,
            MultipartFile descriptionImageFile) throws IOException {

        if (request.getPrice() < 0 || request.getStock() < 0) {
            throw new InvalidProductDataException(INVALID_PRODUCT_MESSAGE);
        }

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

        Product newProduct = productRepository.save(request.toEntity());

        discordWebhook.sendInfoMessage(NEW_PRODUCT_NOTICE + newProduct.getName() + " (ID: " + newProduct.getId() + ")");
        return newProduct;
    }

    @Transactional
    public Product updateProduct(Long id, UpdateProductRequest request,
                                 MultipartFile productImageFile,
                                 MultipartFile descriptionImageFile) throws IOException {
        log.info("Starting updateProduct for product ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id));

        Subcategory subcategory = subcategoryRepository.findById(request.getSubcategoryId())
                .orElseThrow(() -> new SubcategoryNotFoundException(SUBCATEGORY_NOT_FOUND_MESSAGE + request.getSubcategoryId()));

        if (request.getPrice() < 0 || request.getStock() < 0) {
            throw new InvalidProductDataException(INVALID_PRODUCT_MESSAGE);
        }

        StringBuilder changes = new StringBuilder();
        changes.append("상품이 수정되었습니다: ").append(product.getName()).append(" (ID: ").append(product.getId()).append(")\n");

        if (!product.getName().equals(request.getName())) {
            changes.append("이름: ").append(product.getName()).append(" -> ").append(request.getName()).append("\n");
        }

        if (!product.getDescription().equals(request.getDescription())) {
            changes.append("설명: ").append(product.getDescription()).append(" -> ").append(request.getDescription()).append("\n");
        }

        if (product.getPrice() != request.getPrice()) {
            changes.append("가격: ").append(product.getPrice()).append(" -> ").append(request.getPrice()).append("\n");
        }

        if (!product.getExpirationDate().equals(request.getExpirationDate())) {
            changes.append("유효기간: ").append(product.getExpirationDate()).append(" -> ").append(request.getExpirationDate()).append("\n");
        }

        if (product.getDiscountRate() != request.getDiscountRate()) {
            changes.append("할인율: ").append(product.getDiscountRate()).append(" -> ").append(request.getDiscountRate()).append("\n");
        }

        if (product.getStock() != request.getStock()) {
            changes.append("재고: ").append(product.getStock()).append(" -> ").append(request.getStock()).append("\n");
        }

        if (!product.getSubcategory().getId().equals(request.getSubcategoryId())) {
            changes.append("하위 카테고리: ").append(product.getSubcategory().getSubcategoryName()).append(" -> ").append(subcategory.getSubcategoryName()).append("\n");
        }

        String oldProductImageUrl = product.getProductImageUrl();
        String newProductImageUrl = oldProductImageUrl;

        if (productImageFile != null && !productImageFile.isEmpty()) {
            newProductImageUrl = s3Service.uploadProductImage(productImageFile);
            if (oldProductImageUrl != null) {
                s3Service.deleteFile(oldProductImageUrl);
            }
            changes.append("상품 이미지 URL: ").append(oldProductImageUrl).append(" -> ").append(newProductImageUrl).append("\n");
        }

        String oldDescriptionImageUrl = product.getDescriptionImageUrl();
        String newDescriptionImageUrl = oldDescriptionImageUrl;

        if (descriptionImageFile != null && !descriptionImageFile.isEmpty()) {
            newDescriptionImageUrl = s3Service.uploadDescriptionImage(descriptionImageFile);
            if (oldDescriptionImageUrl != null) {
                s3Service.deleteFile(oldDescriptionImageUrl);
            }
            changes.append("설명 이미지 URL: ").append(oldDescriptionImageUrl).append(" -> ").append(newDescriptionImageUrl).append("\n");
        }

        request.setProductImageUrl(newProductImageUrl);
        request.setDescriptionImageUrl(newDescriptionImageUrl);

        product.update(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getExpirationDate(),
                request.getDiscountRate(),
                request.getProductImageUrl(),
                request.getDescriptionImageUrl(),
                request.getStock(),
                request.getSubcategoryId(),
                subcategory);

        String chagesString = changes.toString();
        log.info("Changes detected: {}", chagesString);
        discordWebhook.sendInfoMessage(PRODUCT_CHANGED_NOTICE + product.getName() + " (ID: " + product.getId() + ")");
        return product;
    }

    // product 데이터를 실제로 DB 상에서 삭제(관리자 페이지에서만 사용)
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id));

        // S3 버킷 상에서 상품과 설명 이미지 파일 삭제
        s3Service.deleteFile(product.getProductImageUrl());
        s3Service.deleteFile(product.getDescriptionImageUrl());

        // DB 상에서 product 데이터 삭제
        productRepository.delete(product);

        discordWebhook.sendWarningMessage(PRODUCT_DELETED_NOTICE + product.getName() + " (ID: " + product.getId() + ")");
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
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id));

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
