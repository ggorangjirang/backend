package com.elice.ggorangjirang.carts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemRequest {
    private Long cartId;
    private Long productId;
    private int quantity;
}
