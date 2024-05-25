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
    private String name;
    private float discountRate;
    private int price;
    private String imageUrl;
    private int stock;
    private LocalDate expirationDate;
    private String subcategoryName;
    private String categoryName;
    private String description;
    private boolean isSoldOut;
}
