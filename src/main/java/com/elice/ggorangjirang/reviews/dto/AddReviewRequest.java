package com.elice.ggorangjirang.reviews.dto;

import com.elice.ggorangjirang.reviews.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReviewRequest {
    private String title;
    private String content;
    private String imageUrl;

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}
