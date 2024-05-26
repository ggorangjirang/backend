package com.elice.ggorangjirang.cartitems.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemRequestDto {
    private Long cartId;
    private Long productId;
    private int quantity;
}
