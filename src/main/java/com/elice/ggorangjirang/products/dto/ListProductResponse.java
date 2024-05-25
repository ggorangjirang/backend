package com.elice.ggorangjirang.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListProductResponse {
    private String name;
    private float discountRate;
    private int price;
    private String imageUrl;
    private int stock;
}
