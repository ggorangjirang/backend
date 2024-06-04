package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartItemService {
    CartItemResponse addCartItem(Long cartId, Long productId, int quantity);
    Page<CartItemResponse> getCartItemsByCartId(Long cartId, Pageable pageable);
    CartItemResponse updateCartItem(Long cartItemId, int quantity);
    void deleteCartItem(Long cartItemId);
}
