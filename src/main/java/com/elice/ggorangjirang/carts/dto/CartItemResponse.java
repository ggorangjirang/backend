package com.elice.ggorangjirang.carts.dto;

import com.elice.ggorangjirang.carts.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private Long id;
    private Long productId;
    private String productName;
    private String productImageUrl;
    private int price;
    private float discountRate;
    private int quantity;
    private int discountedPrice; // 할인된 가격 필드 추가

    // 엔티티를 DTO로 변환하는 메서드
    public static CartItemResponse toDto(CartItem cartItem) {
        CartItemResponse dto = new CartItemResponse();
        dto.setId(cartItem.getId());
        dto.setProductId(cartItem.getProduct().getId());
        dto.setProductName(cartItem.getProduct().getName());
        dto.setProductImageUrl(cartItem.getProduct().getProductImageUrl());
        dto.setPrice(cartItem.getProduct().getPrice());
        dto.setDiscountRate(cartItem.getProduct().getDiscountRate());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }
}
