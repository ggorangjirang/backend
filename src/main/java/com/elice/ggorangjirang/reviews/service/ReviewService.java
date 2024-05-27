package com.elice.ggorangjirang.reviews.service;

import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 특정 유저의 아이디를 기준으로 리뷰 가져오기
//    public List<ReviewResponse> getAllReviewsByUserId() {
//        List<Review> reviews = reviewRepository.findReviewsByUserId();
//
//    }


//    @Transactional
//    public Review addReview(AddReviewRequest request) {
//        Product product = productRepository.findById(request.getProductId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
//
//        Users user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
//
//        Review review = Review.builder()
//                .title(request.getTitle())
//                .content(request.getContent())
//                .imageUrl(request.getImageUrl())
//                .product(product)
//                .user(user)
//                .build();
//        return reviewRepository.save(review);
//    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        reviewRepository.deleteById(review.getId());
    }
}
