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
public class CartItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private String productImageUrl;
    private int price;
    private float discountRate;
    private int quantity;

    // 엔티티를 DTO로 변환하는 메서드
    public static CartItemDto toDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
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
