package com.elice.ggorangjirang.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailProductResponse {
    private Long productId;
    private String name;
    private float discountRate;
    private int price;
    private int discountedPrice;
    private String productImageUrl;
    private int stock;
    private LocalDate expirationDate;
    private String subcategoryName;
    private String categoryName;
    private String description;
    private String descriptionImageUrl;
    private boolean isSoldOut;
}
