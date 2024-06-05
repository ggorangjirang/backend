package com.elice.ggorangjirang.reviews.controller;

import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.dto.UpdateReviewRequest;
import com.elice.ggorangjirang.reviews.service.ReviewService;
import com.elice.ggorangjirang.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtService jwtService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<Page<ReviewResponsePublic>> getReviewsByProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<ReviewResponsePublic> reviews = reviewService.getReviewsByProduct(productId, page, size);
        return ResponseEntity.ok(reviews);
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
            @RequestPart("review") AddReviewRequest request,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestHeader("Authorization") String token) throws IOException {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtService.extractEmail(token).get();
        ReviewResponseMy review = reviewService.addReview(email, request, imageFile);
        return ResponseEntity.ok(review);
    }


    @PatchMapping("/users/review/{reviewId}")
    public ResponseEntity<ReviewResponseMy> editReview(
            @PathVariable("reviewId") Long id,
            @RequestPart("review") UpdateReviewRequest request,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestHeader("Authorization") String token) throws IOException {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = jwtService.extractEmail(token).get();
        ReviewResponseMy updatedReview = reviewService.updateReview(email, id, request, imageFile);
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
