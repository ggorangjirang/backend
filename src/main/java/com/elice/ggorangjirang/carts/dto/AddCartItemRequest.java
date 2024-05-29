package com.elice.ggorangjirang.carts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCartItemRequest {
    private Long productId;
    private int quantity;
}
