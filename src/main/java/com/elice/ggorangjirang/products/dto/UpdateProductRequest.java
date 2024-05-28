package com.elice.ggorangjirang.products.dto;

import com.elice.ggorangjirang.products.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {

    private String name;
    private String description;
    private int price;
    private LocalDate expirationDate;
    private float discountRate;
    private String productImageUrl;
    private String descriptionImageUrl;
    private int stock;
    private Long subcategoryId;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .expirationDate(expirationDate)
                .discountRate(discountRate)
                .productImageUrl(productImageUrl)
                .descriptionImageUrl(descriptionImageUrl)
                .stock(stock)
                .subcategoryId(subcategoryId)
                .build();
    }
}