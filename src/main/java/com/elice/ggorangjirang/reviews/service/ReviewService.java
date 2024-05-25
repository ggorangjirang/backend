package com.elice.ggorangjirang.reviews.service;

import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.service.ProductService;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
//    private final UserService userService;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 특정 유저의 아이디를 기준으로 리뷰 가져오기
    /*
    public List<Review> getAllReviewsByUserId() {

    }
     */

    @Transactional
    public Review addReview(AddReviewRequest request) {
        Product product = productService.findProduct(request.getProductId());
//        User user = userService.findUserById(request.getUserId());

        Review review = Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .product(product)
//                .user(user)
                .build();
        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        reviewRepository.deleteById(review.getId());
    }
}
