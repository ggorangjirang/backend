package com.elice.ggorangjirang.reviews.controller;

import com.amazonaws.Response;
import com.elice.ggorangjirang.reviews.dto.ReviewResponse;
import com.elice.ggorangjirang.reviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<Page<ReviewResponse>> getReviewsByProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<ReviewResponse> reviews = reviewService.getReviewsByProduct(productId, page, size);
        return ResponseEntity.ok(reviews);
    }
}
