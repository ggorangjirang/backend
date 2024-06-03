package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartItemService {
    CartItemDto addCartItem(Long cartId, Long productId, int quantity);
    List<CartItemDto> getCartItems(Long cartId);
    Page<CartItemDto> getCartItemsByCartId(Long cartId, Pageable pageable);
    CartItemDto updateCartItem(Long cartItemId, int quantity);
    void deleteCartItem(Long cartItemId);
}
