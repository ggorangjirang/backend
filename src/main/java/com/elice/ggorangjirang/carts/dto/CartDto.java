package com.elice.ggorangjirang.carts.dto;

import com.elice.ggorangjirang.carts.entity.Cart;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CartDto {
    private Long cartId;
    private Long userId;
    private List<CartItemResponse> cartItems;

    // 엔티티를 DTO로 변환
    public static CartDto toDto(Cart cart) {
        return CartDto.builder()
            .cartId(cart.getId())
            .userId(cart.getUser().getId())
            .cartItems(cart.getCartItems().stream()
                .map(CartItemResponse::toDto)
                .collect(Collectors.toList()))
            .build();
    }
}
