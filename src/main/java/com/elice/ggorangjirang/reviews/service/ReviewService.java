package com.elice.ggorangjirang.reviews.service;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.orders.dto.ReviewableOrderItemResponse;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.dto.UpdateReviewRequest;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.elice.ggorangjirang.global.constant.GlobalConstants.*;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    // 상품 상세 페이지 review GET 요청에 대한 DTO 맵핑
    private ReviewResponsePublic convertToReviewResponsePublic(Review review) {
        String userIdentifier = review.getUser().getEmail() != null ?
                review.getUser().getEmail() :
                review.getUser().getSocialId();

        String emailResponse = userIdentifier.substring(0, 3) + "****";

        return new ReviewResponsePublic(
                review.getId(),
                review.getTitle(),
                review.getContent(),
                review.getImageUrl(),
                emailResponse,
                review.getCreatedAt());
    }

    private ReviewResponseMy convertToReviewResponseMy(Review review) {
        return new ReviewResponseMy(
                review.getId(),
                review.getTitle(),
                review.getContent(),
                review.getImageUrl(),
                review.getProduct().getId(),
                review.getProduct().getName(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getUser().getId());
    }

    // 상품 상세 페이지 review GET 요청 전달하기
    public Page<ReviewResponsePublic> getReviewsByProduct(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.findByProduct(productId, pageable);
        return reviewPage.map(this::convertToReviewResponsePublic);
    }

    // 특정 유저의 아이디를 기준으로 리뷰 가져오기
    public Page<ReviewResponseMy> getReviewByUserEmail(String email, int page, int size) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Long userId = user.getId();

        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.findByUserId(userId, pageable);
        return reviewPage.map(this::convertToReviewResponseMy);
    }

    @Transactional
    public Page<ReviewableOrderItemResponse> addReview(String email, String requestJson, MultipartFile imageFile,
                                                       int page, int size) throws IOException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        AddReviewRequest request = objectMapper.readValue(requestJson, AddReviewRequest.class);

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + request.getProductId()));

        boolean hasPurchased = orderItemRepository.existsByUserIdAndProductId(user.getId(), request.getProductId());
        if (!hasPurchased) {
            throw new IllegalStateException(HAS_PURCHASED_MESSAGE);
        }

        String imageUrl = null;
        if(imageFile != null && !imageFile.isEmpty()) {
            imageUrl = s3Service.uploadReviewImage(imageFile);
        }

        Review review = Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(imageUrl)
                .product(product)
                .user(user)
                .build();

        reviewRepository.save(review);

        Pageable pageable = PageRequest.of(page, size);
        Page<OrderItem> deliveredOrderItems = orderItemRepository.findByOrder_Users_EmailAndOrder_Deliveries_Status(email, "DELIVERY_COMPLETE", pageable);

        Long userId = user.getId();

        List<ReviewableOrderItemResponse> reviewableItems = deliveredOrderItems.getContent().stream()
                .filter(orderItem -> !reviewRepository.existsByProduct_IdAndUser_Email(orderItem.getProduct().getId(), email))
                .map(orderItem -> new ReviewableOrderItemResponse(
                        orderItem.getProduct().getId(),
                        userId,
                        orderItem.getProduct().getName(),
                        orderItem.getProduct().getProductImageUrl(),
                        orderItem.getOrderPrice(),
                        orderItem.getQuantity(),
                        orderItem.getTotalPrice()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(reviewableItems, pageable, deliveredOrderItems.getTotalElements());
    }

    @Transactional
    public Page<ReviewResponseMy> updateReview(String email, Long id, String requestJson, MultipartFile imageFile,
                                               int page, int size) throws IOException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_MESSAGE + id));

        UpdateReviewRequest request = objectMapper.readValue(requestJson, UpdateReviewRequest.class);

        if (!review.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("리뷰 수정 권한이 없습니다.");
        }

        String oldImageUrl = review.getImageUrl();
        String newImageUrl = oldImageUrl;

        if (imageFile != null && !imageFile.isEmpty()) {
            newImageUrl = s3Service.uploadReviewImage(imageFile);
            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                s3Service.deleteFile(oldImageUrl);
            }
        }

        review.update(
                request.getTitle(),
                request.getContent(),
                newImageUrl);

        reviewRepository.save(review);

        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.findByUserId(user.getId(), pageable);
        return reviewPage.map(this::convertToReviewResponseMy);
    }

    @Transactional
    public Page<ReviewResponseMy> deleteReview(String email, Long id, int page, int size) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_MESSAGE + id));

        if(!review.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("리뷰 삭제 권한이 없습니다.");
        }

        if(review.getImageUrl() != null && !review.getImageUrl().isEmpty()) {
            s3Service.deleteFile(review.getImageUrl());
        }

        reviewRepository.delete(review);

        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.findByUserId(user.getId(), pageable);
        return reviewPage.map(this::convertToReviewResponseMy);
    }
}
