package com.elice.ggorangjirang.products.dto;

import com.elice.ggorangjirang.products.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {

    private String name;
    private String description;
    private int price;
    private LocalDateTime expirationDate;
    private float discountRate;
    private String imageUrl;
    private int stock;
    private Long subcategoryId;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .expirationDate(expirationDate)
                .discountRate(discountRate)
                .imageUrl(imageUrl)
                .stock(stock)
                .subcategoryId(subcategoryId)
                .build();
    }
}