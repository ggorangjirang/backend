package com.elice.ggorangjirang.carts.dto;

import com.elice.ggorangjirang.carts.entity.Cart;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDto {
    private Long cartId;
    private Long productId;
    private Long userId;
    private int cartCount;
    private String productName;
    private int productPrice;

    // DTO를 엔티티로 변환
    public static Cart toEntity(CartDto cartDto) {
        return Cart.builder()
            .cartId(cartDto.getCartId())
            .cartCount(cartDto.getCartCount())
            .build();
    }

    // 엔티티를 DTO로 변환
    public static CartDto fromEntity(Cart cart, String productName, int productPrice) {
        return CartDto.builder()
            .cartId(cart.getCartId())
            .productId(cart.getProduct().getId())
//            .userId(cart.getUser().getId())
            .cartCount(cart.getCartCount())
            .productName(productName)
            .productPrice(productPrice)
            .build();
    }

    // 엔티티를 DTO로 변환 (productName과 productPrice가 필요 없는 경우)
    public static CartDto fromEntity(Cart cart) {
        return CartDto.builder()
            .cartId(cart.getCartId())
            .productId(cart.getProduct().getId())
//            .userId(cart.getUser().getId())
            .cartCount(cart.getCartCount())
            .build();
    }
}

