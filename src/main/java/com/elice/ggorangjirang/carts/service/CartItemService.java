package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import org.springframework.data.domain.Page;

public interface CartItemService {
    CartItemResponse addCartItem(Long cartId, Long productId, int quantity);
    Page<CartItemResponse> getCartItemsByCartId(Long cartId, int page, int size); // 수정된 메서드
    CartItemResponse updateCartItem(Long cartItemId, int quantity);
    void deleteCartItem(Long cartItemId);
}
