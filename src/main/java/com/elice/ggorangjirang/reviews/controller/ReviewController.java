package com.elice.ggorangjirang.reviews.controller;

import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.orders.dto.ReviewableOrderItemResponse;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtService jwtService;
    private final OrderItemService orderItemService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<Page<ReviewResponsePublic>> getReviewsByProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<ReviewResponsePublic> reviews = reviewService.getReviewsByProduct(productId, page, size);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/users/reviewable-items")
    public ResponseEntity<Page<ReviewableOrderItemResponse>> getReviewableOrderItems(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestHeader("Authorization") String token) {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = emailOptional.get();
        Page<ReviewableOrderItemResponse> reviewableItems = orderItemService.getReviewableOrderItems(email, page, size);
        return ResponseEntity.ok(reviewableItems);

    }

    @GetMapping("/users/my-reviews")
    public ResponseEntity<Page<ReviewResponseMy>> getReviewByUserId(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestHeader("Authorization") String token) {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = emailOptional.get();
        Page<ReviewResponseMy> reviews = reviewService.getReviewByUserEmail(email, page, size);
        return ResponseEntity.ok(reviews);
    }


    @PostMapping("/users/review")
    public ResponseEntity<ReviewResponseMy> addReview(
            @RequestPart("review") String requestJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestHeader("Authorization") String token) throws IOException {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtService.extractEmail(token).get();
        ReviewResponseMy review = reviewService.addReview(email, requestJson, imageFile);
        return ResponseEntity.ok(review);
    }


    @PatchMapping("/users/review/{reviewId}")
    public ResponseEntity<ReviewResponseMy> editReview(
            @PathVariable("reviewId") Long id,
            @RequestPart("review") String requestJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestHeader("Authorization") String token) throws IOException {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtService.extractEmail(token).get();
        ReviewResponseMy updatedReview = reviewService.updateReview(email, id, requestJson, imageFile);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/users/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long id,
                                             @RequestHeader("Authorization") String token) {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtService.extractEmail(token).get();
        reviewService.deleteReview(email, id);
        return ResponseEntity.noContent().build();
    }
}
