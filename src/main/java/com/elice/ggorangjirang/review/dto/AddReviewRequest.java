package com.elice.ggorangjirang.review.dto;

import com.elice.ggorangjirang.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReviewRequest {
    private String content;

    private String imageUrl;

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}
