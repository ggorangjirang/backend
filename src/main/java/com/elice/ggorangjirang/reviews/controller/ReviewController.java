package com.elice.ggorangjirang.reviews.controller;

import com.elice.ggorangjirang.orders.dto.ReviewableOrderItemResponse;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderItemService orderItemService;

    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            return (String) principal;
        }

        return null;
    }

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
            @RequestParam(name = "size", defaultValue = "5") int size) {

        String email = getAuthenticatedUserEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Page<ReviewableOrderItemResponse> reviewableItems = orderItemService.getReviewableOrderItems(email, page, size);
        return ResponseEntity.ok(reviewableItems);

    }

    @GetMapping("/users/my-reviews")
    public ResponseEntity<Page<ReviewResponseMy>> getReviewByUserId(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size) {

        String email = getAuthenticatedUserEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Page<ReviewResponseMy> reviews = reviewService.getReviewByUserEmail(email, page, size);
        return ResponseEntity.ok(reviews);
    }


    @PostMapping("/users/review")
    public ResponseEntity<Page<ReviewableOrderItemResponse>> addReview(
            @RequestPart("review") String requestJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws IOException {

        String email = getAuthenticatedUserEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Page<ReviewableOrderItemResponse> reviewableItems = reviewService.addReview(email, requestJson, imageFile, page, size);
        return ResponseEntity.ok(reviewableItems);
    }


    @PatchMapping("/users/review/{reviewId}")
    public ResponseEntity<Page<ReviewResponseMy>> editReview(
            @PathVariable("reviewId") Long id,
            @RequestPart("review") String requestJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws IOException {

        String email = getAuthenticatedUserEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Page<ReviewResponseMy> updatedReview = reviewService.updateReview(email, id, requestJson, imageFile, page, size);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/users/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long id) {

        String email = getAuthenticatedUserEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        reviewService.deleteReview(email, id);
        return ResponseEntity.noContent().build();
    }
}
