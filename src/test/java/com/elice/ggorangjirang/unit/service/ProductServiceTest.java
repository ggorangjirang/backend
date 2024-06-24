package com.elice.ggorangjirang.unit.service;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.discord.DiscordWebhook;
import com.elice.ggorangjirang.products.dto.AddProductRequest;
import com.elice.ggorangjirang.products.dto.DetailProductResponse;
import com.elice.ggorangjirang.products.dto.ListProductResponse;
import com.elice.ggorangjirang.products.dto.UpdateProductRequest;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.exception.InvalidProductDataException;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.products.service.ProductService;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import com.elice.ggorangjirang.subcategories.repository.SubcategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SubcategoryRepository subcategoryRepository;

    @Mock
    private S3Service s3Service;

    @Mock
    private DiscordWebhook discordWebhook;

    @InjectMocks
    private ProductService productService;
    private Subcategory subcategory;
    private Product product;

    @BeforeEach
    void setUp() {
        // 서브카테고리 객체 생성 및 설정
        subcategory = new Subcategory();
        subcategory.setId(1L);
        subcategory.setSubcategoryName("Test Subcategory");

        // 상품 객체 생성 및 설정
        product = Product.builder()
                .name("Test Product")
                .description("Test Description")
                .price(100)
                .expirationDate(LocalDate.now())
                .discountRate(10.0f)
                .productImageUrl("test_product_image_url")
                .descriptionImageUrl("test_description_image_url")
                .stock(10)
                .subcategoryId(1L)
                .subcategory(subcategory)
                .build();
    }

    @Test
    @DisplayName("상품 추가")
    void createProduct() throws IOException {
        // 상품 추가 요청 DTO 객체 생성
        AddProductRequest request = new AddProductRequest(
                "Test Product", "Test Description", 100, LocalDate.now(), 10.0f,
                "test_product_image_url", "test_description_image_url", 10, 1L);

        // MultipartFile 객체 모킹
        MultipartFile productImageFile = mock(MultipartFile.class);
        MultipartFile descriptionImageFile = mock(MultipartFile.class);

        // 메서드 모킹
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(s3Service.uploadProductImage(productImageFile)).thenReturn("uploaded_product_image_url");
        when(s3Service.uploadDescriptionImage(descriptionImageFile)).thenReturn("uploaded_description_image_url");

        Product createdProduct = productService.createProduct(request, productImageFile, descriptionImageFile);

        // 결과 검증
        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
        verify(discordWebhook, times(1)).sendInfoMessage(anyString());
    }

    @Test
    @DisplayName("상품 추가 - 올바르지 않은 정보 입력 시")
    void createProductInvalidProductDataException() {
        // 가격이 음수인 요청 객체 생성
        AddProductRequest request = new AddProductRequest(
                "Test Product", "Test Description", -100, LocalDate.now(), 10.0f,
                "test_product_image_url", "test_description_image_url", 10, 1L);

        MultipartFile productImageFile = mock(MultipartFile.class);
        MultipartFile descriptionImageFile = mock(MultipartFile.class);

        // 예외 발생 여부 검증
        assertThrows(InvalidProductDataException.class, () -> {
            productService.createProduct(request, productImageFile, descriptionImageFile);
        });
    }

    @Test
    @DisplayName("상품 수정")
    void updateProduct() throws IOException {
        // 상품 수정 요청 DTO 객체 생성
        UpdateProductRequest request = new UpdateProductRequest(
                "Updated Product", "Updated Description", 200, LocalDate.now().plusDays(1), 15.0f,
                "updated_product_image_url", "updated_description_image_url", 20, 1L);

        MultipartFile productImageFile = mock(MultipartFile.class);
        MultipartFile descriptionImageFile = mock(MultipartFile.class);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(subcategoryRepository.findById(anyLong())).thenReturn(Optional.of(subcategory));
        when(s3Service.uploadProductImage(productImageFile)).thenReturn("uploaded_product_image_url");
        when(s3Service.uploadDescriptionImage(descriptionImageFile)).thenReturn("uploaded_description_image_url");

        Product updatedProduct = productService.updateProduct(1L, request, productImageFile, descriptionImageFile);

        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
        verify(productRepository, times(1)).findById(anyLong());
        verify(discordWebhook, times(1)).sendInfoMessage(anyString());
    }

    @Test
    @DisplayName("상품 수정 - 상품이 존재하지 않을 시")
    void updateProductProductNotFoundException() {
        // 존재하지 않는 상품에 대한 상품 수정 요청
        UpdateProductRequest request = new UpdateProductRequest(
                "Updated Product", "Updated Description", 200, LocalDate.now().plusDays(1), 15.0f,
                "updated_product_image_url", "updated_description_image_url", 20, 1L);

        MultipartFile productImageFile = mock(MultipartFile.class);
        MultipartFile descriptionImageFile = mock(MultipartFile.class);

        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // 예외 발생 여부 검증
        assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProduct(1L, request, productImageFile, descriptionImageFile);
        });
    }

    @Test
    @DisplayName("상품 삭제")
    void deleteProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).delete(any(Product.class));
        /* verify(s3Service, times(1)).deleteFile(anyString()); */
        verify(discordWebhook, times(1)).sendWarningMessage(anyString());
    }

    @Test
    @DisplayName("상품 삭제 - 상품이 존재하지 않을 시")
    void deleteProductProductNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(1L);
        });
    }

    @Test
    @DisplayName("단일 상품 조회")
    void findProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Product foundProduct = productService.findProduct(1L);

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("단일 상품 조회 - 상품이 존재하지 않을 시")
    void findProductProductNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.findProduct(1L);
        });
    }

    @Test
    @DisplayName("한정세일상품 조회")
    void getAllLimitedSaleProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findLimitedSaleProducts(pageable)).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getAllLimitedSaleProducts(0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("인기상품 조회")
    void getAllBestSellingProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findBestSellingProducts(pageable)).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getAllBestSellingProducts(0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("상품 주문량순 조회")
    void getProductsByOrderCountDesc() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByOrderCountDesc(pageable)).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsByOrderCountDesc(0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("상품 최신순 조회")
    void getProductsByCreatedAtDesc() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByCreatedAtDesc(pageable)).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsByCreatedAtDesc(0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("상품 낮은 가격순 조회")
    void getProductsByPriceAsc() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByPriceAsc(pageable)).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsByPriceAsc(0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("상품 높은 가격순 조회")
    void getProductsByPriceDesc() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByPriceDesc(pageable)).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsByPriceDesc(0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("상품 조회수순 조회")
    void getProductsByViewCountDesc() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByViewCountDesc(pageable)).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsByViewCountDesc(0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("카테고리별 상품 조회")
    void getProductsByCategory() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByCategory(anyLong(), any(Pageable.class))).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsByCategory(1L, 0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("서브카테고리별 상품 조회")
    void getProductsBySubcategory() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findBySubcategory(eq(1L), any(Pageable.class))).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsBySubcategory(1L, 0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("검색창을 통한 상품 조회")
    void getProductsByKeyword() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByKeyword(anyString(), any(Pageable.class))).thenReturn(productPage);

        Page<ListProductResponse> responsePage = productService.getProductsByKeyword("Test", 0, 10);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(product.getName(), responsePage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("상품 상세 페이지 정보")
    void getProductDetail() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        DetailProductResponse response = productService.getProductDetail(anyLong());

        assertNotNull(response);
        assertEquals(product.getId(), response.getProductId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getDiscountRate(), response.getDiscountRate());
        assertEquals(product.getPrice(), response.getPrice());
        assertEquals(product.getProductImageUrl(), response.getProductImageUrl());
        assertEquals(product.getStock(), response.getStock());
        assertEquals(product.getExpirationDate(), response.getExpirationDate());
        assertEquals(product.getSubcategory().getSubcategoryName(), response.getSubcategoryName());
        assertNull(response.getCategoryName()); // 테스트 단순화를 위해 상위 카테고리명 미적용
        assertEquals(product.getDescription(), response.getDescription());
        assertEquals(product.getDescriptionImageUrl(), response.getDescriptionImageUrl());
        assertFalse(response.isSoldOut());  // 품절 상품이 아닌 경우를 가정

        verify(productRepository, times(1)).findById(anyLong());
    }
}
