package com.elice.ggorangjirang.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewResponsePublic {
    private String title;
    private String content;
    private String imageUrl;
    private String emailResponse;
    private LocalDateTime createdAt;
}