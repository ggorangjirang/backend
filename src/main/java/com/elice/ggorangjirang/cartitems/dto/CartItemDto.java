package com.elice.ggorangjirang.cartitems.dto;

import com.elice.ggorangjirang.cartitems.entity.CartItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDto {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private String productImageUrl;
    private int productPrice;
    private int originalProductPrice;
    private int quantity;

    public static CartItemDto fromEntity(CartItem cartItem) {
        return CartItemDto.builder()
            .cartItemId(cartItem.getCartItemId())
            .productId(cartItem.getProduct().getId())
            .productName(cartItem.getProduct().getName())
            .productImageUrl(cartItem.getProduct().getImageUrl())
            .productPrice(cartItem.getProduct().getPrice())
//            .originalProductPrice(cartItem.getProduct().getOriginalPrice())
            .quantity(cartItem.getQuantity())
            .build();
    }
}
