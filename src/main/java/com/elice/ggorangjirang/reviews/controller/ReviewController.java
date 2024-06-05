package com.elice.ggorangjirang.reviews.controller;

import com.elice.ggorangjirang.jwt.util.CustomUserDetails;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.dto.UpdateReviewRequest;
import com.elice.ggorangjirang.reviews.service.ReviewService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepository userRepository;

    private Users getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        if (!(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new IllegalStateException("잘못된 사용자 정보입니다.");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("사용자 정보를 찾을 수 없습니다."));
    }

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
            @RequestParam(name = "size", defaultValue = "3") int size) {

        Users user = getAuthenticatedUser();
        Page<ReviewResponseMy> reviews = reviewService.getReviewByUserId(user, page, size);
        return ResponseEntity.ok(reviews);
    }


    @PostMapping("/users/review")
    public ResponseEntity<ReviewResponseMy> addReview(
            @RequestPart("review") AddReviewRequest request,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        Users user = getAuthenticatedUser();
        ReviewResponseMy review = reviewService.addReview(user, request, imageFile);
        return ResponseEntity.ok(review);
    }


    @PatchMapping("/users/review/{reviewId}")
    public ResponseEntity<ReviewResponseMy> editReview(
            @PathVariable("reviewId") Long id,
            @RequestPart("review") UpdateReviewRequest request,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        Users user = getAuthenticatedUser();
        ReviewResponseMy updatedReview = reviewService.updateReview(user, id, request, imageFile);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/users/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long id) {

        Users user = getAuthenticatedUser();
        reviewService.deleteReview(user, id);
        return ResponseEntity.noContent().build();
    }
}
