package com.elice.ggorangjirang.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewResponseMy {
    private Long reviewId;
    private String title;
    private String content;
    private String imageUrl;
    private String productName;
}
