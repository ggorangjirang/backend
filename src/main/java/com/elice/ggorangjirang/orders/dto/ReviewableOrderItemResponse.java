package com.elice.ggorangjirang.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewableOrderItemResponse {
    private Long productId;
    private Long userId;
    private String productName;
    private String imageUrl;
    private int orderPrice;
    private int quantity;
    private int totalPrice;
}
