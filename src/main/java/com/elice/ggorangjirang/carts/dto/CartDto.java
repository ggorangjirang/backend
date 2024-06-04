package com.elice.ggorangjirang.carts.dto;

import com.elice.ggorangjirang.carts.entity.Cart;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class CartDto {
    private Long cartId;
    private Long userId;
    private Page<CartItemResponse> cartItems;

    public static CartDto toDto(Cart cart, Page<CartItemResponse> cartItemResponses) {
        return CartDto.builder()
            .cartId(cart.getId())
            .userId(cart.getUser().getId())
            .cartItems(cartItemResponses)
            .build();
    }
}
