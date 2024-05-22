package com.elice.ggorangjirang.reviews.service;

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

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 특정 유저의 아이디를 기준으로 리뷰 가져오기
//    public List<Review> getAllReviewsByUserId() {
//
//    }

    @Transactional
    public Review addReview(AddReviewRequest request) {
        return reviewRepository.save(request.toEntity());
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        reviewRepository.deleteById(review.getId());
    }
}
