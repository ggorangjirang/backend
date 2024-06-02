package com.elice.ggorangjirang.carts.dto;

import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.users.entity.Users;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CartDto {
    private Long cartId;
    private Long userId;
    private List<CartItemDto> cartItems;

    // DTO를 엔티티로 변환
    public static Cart toEntity(CartDto cartDto) {
        // Users 객체를 추가하여 userId를 설정합니다.
        Users user = Users.builder().id(cartDto.getUserId()).build();

        return Cart.builder()
            .id(cartDto.getCartId())
            .user(user)
            .build();
    }

    // 엔티티를 DTO로 변환
    public static CartDto toDto(Cart cart) {
        return CartDto.builder()
            .cartId(cart.getId())
            .userId(cart.getUser().getId())
            .cartItems(cart.getCartItems().stream()
                .map(CartItemDto::toDto)
                .collect(Collectors.toList()))
            .build();
    }
}
