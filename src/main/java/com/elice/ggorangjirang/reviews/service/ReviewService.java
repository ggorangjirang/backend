package com.elice.ggorangjirang.reviews.service;

import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.dto.ReviewResponse;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    private ReviewResponse convertToReviewResponse(Review review) {
        return new ReviewResponse(
                review.getTitle(),
                review.getContent(),
                review.getImageUrl(),
                review.getUser().getEmail(),
                review.getCreatedAt());
    }

    // 특정 상품을 기준으로 리뷰 가져오기
    public Page<ReviewResponse> getReviewsByProduct(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.findByProduct(productId, pageable);
        return reviewPage.map(this::convertToReviewResponse);
    }

    // 특정 유저의 아이디를 기준으로 리뷰 가져오기
//    public List<ReviewResponse> getAllReviewsByUserId() {
//        List<Review> reviews = reviewRepository.findReviewsByUserId();
//
//    }

    @Transactional
    public Review addReview(AddReviewRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + request.getProductId()));

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + request.getUserId()));

//        boolean hasPurchased = orderItemRepository.existsByUserIdAndProductId(request.getUserId(), request.getProductId());
//        if (!hasPurchased) {
//            throw new IllegalStateException("상품 구매 내역이 없습니다.");
//        }

        Review review = Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .product(product)
                .user(user)
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        reviewRepository.delete(review);
    }
}
