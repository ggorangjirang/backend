package com.elice.ggorangjirang.reviews.controller;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.dto.UpdateReviewRequest;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.reviews.service.ReviewService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final UserService userService;
    private final S3Service s3Service;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<Page<ReviewResponsePublic>> getReviewsByProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<ReviewResponsePublic> reviews = reviewService.getReviewsByProduct(productId, page, size);
        return ResponseEntity.ok(reviews);
    }

    // TODO: UserDetails 구현 완료 후 주석 해제
//    @GetMapping("/users/my-reviews")
//    public ResponseEntity<Page<ReviewResponseMy>> getReviewByUserId(
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "3") int size) {
//
//        Page<ReviewResponseMy> reviews = reviewService.getReviewByUserId(page, size);
//        return ResponseEntity.ok(reviews);
//    }
//
//
//    @PostMapping("/users/review")
//    public ResponseEntity<ReviewResponseMy> addReview(
//            @RequestPart("review") AddReviewRequest request,
//            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
//
//        ReviewResponseMy review = reviewService.addReview(request, imageFile);
//        return ResponseEntity.ok(review);
//    }
//
//
//    @PatchMapping("/users/review/{reviewId}")
//    public ResponseEntity<ReviewResponseMy> editReview(
//            @PathVariable("reviewId") Long id,
//            @RequestPart("review") UpdateReviewRequest request,
//            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
//
//        ReviewResponseMy updatedReview = reviewService.updateReview(id, request, imageFile);
//        return ResponseEntity.ok(updatedReview);
//    }
//
//    @DeleteMapping("/users/review/{reviewId}")
//    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long id) {
//
//        reviewService.deleteReview(id);
//        return ResponseEntity.noContent().build();
//    }
}
