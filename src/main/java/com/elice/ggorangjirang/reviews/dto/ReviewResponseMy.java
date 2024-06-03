package com.elice.ggorangjirang.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewResponseMy {
    private Long reviewId;
    private String title;
    private String content;
    private String imageUrl;
    private Long productId;
    private String productName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
}
