package com.elice.ggorangjirang.unit.service;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.orders.dto.ReviewableOrderItemResponse;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.dto.UpdateReviewRequest;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import com.elice.ggorangjirang.reviews.service.ReviewService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private S3Service s3Service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ReviewService reviewService;

    private Users user;
    private Product product;
    private Review review;
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        // 회원 객체 생성 및 설정
        user = Users.builder()
                .id(1L)
                .email("test@test.com")
                .build();

        // 상품 객체 생성 및 설정
        product = Product.builder()
                .id(1L)
                .name("상품1")
                .build();

        // 리뷰 객체 생성 및 설정
        review = new Review();
        review.setId(1L);
        review.setTitle("테스트 리뷰 제목");
        review.setContent("테스트 리뷰 내용 : @@@@@");
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        review.setImageUrl("test_image_url");
        review.setProduct(product);
        review.setUser(user);

        // 주문상품 객체 생성 및 설정
        orderItem = OrderItem.createOrderItem(product, 100, 1);
    }

    @Test
    @DisplayName("리뷰 추가")
    void addReview() throws IOException {
        AddReviewRequest request = new AddReviewRequest("리뷰 생성 테스트 제목", "리뷰 생성 테스트 내용", 1L);
        MultipartFile imageFile = mock(MultipartFile.class);
        String requestJson = "{}";
        when(userRepository.findByEmail(eq("test@test.com"))).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderItemRepository.existsByUserIdAndProductId(1L, 1L)).thenReturn(true);
        when(s3Service.uploadReviewImage(imageFile)).thenReturn("uploaded_review_image_url");
        when(objectMapper.readValue(anyString(), eq(AddReviewRequest.class))).thenReturn(request);
        Page<OrderItem> orderItemsPage = new PageImpl<>(List.of(orderItem));
        when(orderItemRepository.findByOrder_Users_EmailAndOrder_Deliveries_Status(anyString(), eq("DELIVERY_COMPLETE"), any(Pageable.class)))
                .thenReturn(orderItemsPage);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Page<ReviewableOrderItemResponse> result = reviewService.addReview("test@test.com", requestJson, imageFile, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getProductId());
        assertEquals(1L, result.getContent().get(0).getUserId());
        assertEquals("상품1", result.getContent().get(0).getProductName());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    @DisplayName("리뷰 수정")
    void updateReview() throws IOException {
        MultipartFile imageFile = mock(MultipartFile.class);
        String requestJson = "{}";
        when(userRepository.findByEmail(eq("test@test.com"))).thenReturn(Optional.of(user));
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(objectMapper.readValue(anyString(), eq(UpdateReviewRequest.class)))
                .thenReturn(new UpdateReviewRequest("수정된 제목", "수정된 내용"));
        when(s3Service.uploadReviewImage(imageFile)).thenReturn("uploaded_review_image_url");
        when(reviewRepository.findByUserId(anyLong(), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(review))); // Mock setup for findByUserId

        Page<ReviewResponseMy> result = reviewService.updateReview("test@test.com", 1L, requestJson, imageFile, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("수정된 제목", result.getContent().get(0).getTitle());
        assertEquals("수정된 내용", result.getContent().get(0).getContent());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    @DisplayName("리뷰 삭제")
    void deleteReview() {
        when(userRepository.findByEmail(eq("test@test.com"))).thenReturn(Optional.of(user));
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.findByUserId(anyLong(), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(review)));

        Page<ReviewResponseMy> result = reviewService.deleteReview("test@test.com", 1L, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(reviewRepository, times(1)).delete(any(Review.class));
    }

    @Test
    @DisplayName("리뷰 수정 - 리뷰를 찾을 수 없는 경우")
    void updateReview_reviewNotFound() throws IOException {
        MultipartFile imageFile = mock(MultipartFile.class);
        String requestJson = "{}";
        when(userRepository.findByEmail(eq("test@test.com"))).thenReturn(Optional.of(user));
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.updateReview("test@test.com", 1L, requestJson, imageFile, 0, 10);
        });

        verify(reviewRepository, never()).save(any(Review.class));
    }


    @Test
    @DisplayName("리뷰 조회 - 상품별")
    void getReviewsByProduct() {
        Page<Review> reviewPage = new PageImpl<>(List.of(review));
        when(reviewRepository.findByProduct(anyLong(), any(Pageable.class))).thenReturn(reviewPage);

        Page<ReviewResponsePublic> result = reviewService.getReviewsByProduct(1L, 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("테스트 리뷰 제목", result.getContent().get(0).getTitle());
    }

    @Test
    @DisplayName("리뷰 조회 - 유저별")
    void getReviewByUserEmail() {
        Page<Review> reviewPage = new PageImpl<>(List.of(review));
        when(userRepository.findByEmail(eq("test@test.com"))).thenReturn(Optional.of(user));
        when(reviewRepository.findByUserId(anyLong(), any(Pageable.class))).thenReturn(reviewPage);

        Page<ReviewResponseMy> result = reviewService.getReviewByUserEmail("test@test.com", 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("테스트 리뷰 제목", result.getContent().get(0).getTitle());
    }
}
