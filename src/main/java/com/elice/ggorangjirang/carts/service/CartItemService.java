package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartItemService {
    CartItemResponse addCartItem(Long cartId, Long productId, int quantity);
    List<CartItemResponse> getCartItems(Long cartId);
    Page<CartItemResponse> getCartItemsByCartId(Long cartId, Pageable pageable);
    CartItemResponse updateCartItem(Long cartItemId, int quantity);
    void deleteCartItem(Long cartItemId);
}