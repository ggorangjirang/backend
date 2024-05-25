package com.elice.ggorangjirang.cartitems.dto;

import com.elice.ggorangjirang.cartitems.entity.CartItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
    private Long cartItemId;
    private Long cartId;
    private Long productId;
    private String productName;
    private int quantity;
    private int price;
    private float discountRate;
    private String imageUrl;

    // CartItem 엔티티를 DTO로 변환하는 메서드
    public static CartItemDto fromEntity(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setCartItemId(cartItem.getCartItemId());
        dto.setCartId(cartItem.getCart().getId());
        dto.setProductId(cartItem.getProduct().getId());
        dto.setProductName(cartItem.getProduct().getName());
        dto.setQuantity(cartItem.getQuantity());
        dto.setPrice(cartItem.getProduct().getPrice());
        dto.setDiscountRate(cartItem.getProduct().getDiscountRate());
        dto.setImageUrl(cartItem.getProduct().getImageUrl());
        return dto;
    }

    // DTO를 CartItem 엔티티로 변환하는 메서드
    public CartItem toEntity() {
        return CartItem.builder()
            .quantity(this.quantity)
            .build();
    }
}