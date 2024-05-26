package com.elice.ggorangjirang.cartitems.service;

import com.elice.ggorangjirang.cartitems.dto.CartItemDto;

import java.util.List;

public interface CartItemService {
    CartItemDto addCartItem(Long cartId, Long productId, int quantity);
    List<CartItemDto> getCartItems(Long cartId);
    CartItemDto updateCartItem(Long cartItemId, int quantity);
    void deleteCartItem(Long cartItemId);
}
