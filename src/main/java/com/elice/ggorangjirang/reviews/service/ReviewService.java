package com.elice.ggorangjirang.reviews.service;

import com.elice.ggorangjirang.amazonS3.service.S3Service;
import com.elice.ggorangjirang.jwt.util.CustomUserDetails;
import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.products.service.ProductService;
import com.elice.ggorangjirang.reviews.dto.AddReviewRequest;
import com.elice.ggorangjirang.reviews.dto.ReviewResponseMy;
import com.elice.ggorangjirang.reviews.dto.ReviewResponsePublic;
import com.elice.ggorangjirang.reviews.dto.UpdateReviewRequest;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final S3Service s3Service;

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found with id: ";
    private static final String REVIEW_NOT_FOUND_MESSAGE = "Review not found with id: ";
    private static final String HAS_PURCHASED_MESSAGE = "There is no purchase history for this product.";


    // 인증된 사용자 정보 추출 메서드
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

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_MESSAGE + id));
        return review;
    }

    // 상품 상세 페이지 review GET 요청에 대한 DTO 맵핑
    private ReviewResponsePublic convertToReviewResponsePublic(Review review) {
        String userIdentifier = review.getUser().getEmail() != null ?
                review.getUser().getEmail() :
                review.getUser().getSocialId();

        String emailResponse = userIdentifier.substring(0, 3) + "****";

        return new ReviewResponsePublic(
                review.getId(),
                review.getTitle(),
                review.getContent(),
                review.getImageUrl(),
                emailResponse,
                review.getCreatedAt());
    }

    private ReviewResponseMy convertToReviewResponseMy(Review review) {
        return new ReviewResponseMy(
                review.getId(),
                review.getTitle(),
                review.getContent(),
                review.getImageUrl(),
                review.getProduct().getId(),
                review.getProduct().getName(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getUser().getId());
    }

    // 상품 상세 페이지 review GET 요청 전달하기
    public Page<ReviewResponsePublic> getReviewsByProduct(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.findByProduct(productId, pageable);
        return reviewPage.map(this::convertToReviewResponsePublic);
    }

    // 특정 유저의 아이디를 기준으로 리뷰 가져오기
    public Page<ReviewResponseMy> getReviewByUserId(int page, int size) {
        Users user = getAuthenticatedUser();
        Pageable pageable = PageRequest.of(page, size);

        Page<Review> reviewPage = reviewRepository.findByUserId(user.getId(), pageable);
        return reviewPage.map(this::convertToReviewResponseMy);
    }

    @Transactional
    public ReviewResponseMy addReview(AddReviewRequest request, MultipartFile imageFile) throws IOException {
        Users user = getAuthenticatedUser();
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + request.getProductId()));

        boolean hasPurchased = orderItemRepository.existsByUserIdAndProductId(request.getUserId(), request.getProductId());
        if (!hasPurchased) {
            throw new IllegalStateException(HAS_PURCHASED_MESSAGE);
        }

        String imageUrl = null;
        if(imageFile != null && !imageFile.isEmpty()) {
            imageUrl = s3Service.uploadReviewImage(imageFile);
        }

        Review review = Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(imageUrl)
                .product(product)
                .user(user)
                .build();

        reviewRepository.save(review);
        return convertToReviewResponseMy(review);
    }

    @Transactional
    public ReviewResponseMy updateReview(Long id, UpdateReviewRequest request, MultipartFile imageFile) throws IOException {
        Users user = getAuthenticatedUser();
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_MESSAGE + id));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("리뷰 수정 권한이 없습니다.");
        }

        String oldImageUrl = review.getImageUrl();
        String newImageUrl = oldImageUrl;

        if (imageFile != null && !imageFile.isEmpty()) {
            newImageUrl = s3Service.uploadReviewImage(imageFile);
            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                s3Service.deleteFile(oldImageUrl);
            }
        }

        review.update(
                request.getTitle(),
                request.getContent(),
                newImageUrl);

        reviewRepository.save(review);
        return convertToReviewResponseMy(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        Users user = getAuthenticatedUser();
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_MESSAGE + id));

        if(!review.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("리뷰 삭제 권한이 없습니다.");
        }
        reviewRepository.delete(review);
    }
}
