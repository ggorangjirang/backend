package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.carts.exception.CartNotFoundException;
import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void createCartForUser(Users user) {
        if (cartRepository.findByUserId(user.getId()).isEmpty()) {
            Cart cart = Cart.builder()
                .user(user)
                .build();
            cartRepository.save(cart);
        }
    }

    @Transactional(readOnly = true)
    public CartDto getCartItems(Long userId, Pageable pageable) {
        Cart cart = cartRepository.findByUserId(userId)
            .orElseThrow(() -> new CartNotFoundException("장바구니가 존재하지 않습니다."));
        Page<CartItem> cartItemsPage = cartItemRepository.findByCartId(cart.getId(), pageable);
        Page<CartItemResponse> cartItemResponsesPage = cartItemsPage.map(CartItemResponse::toDto);
        return CartDto.toDto(cart, cartItemResponsesPage);
    }
}
