package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.carts.exception.CartNotFoundException;
import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public void createCartForUser(Users user) {
        if (cartRepository.findByUserId(user.getId()) == null || cartRepository.findByUserId(user.getId()).isEmpty()) {
            Cart cart = Cart.builder()
                .user(user)
                .build();
            cartRepository.save(cart);
        }
    }

    @Transactional(readOnly = true)
    public CartDto getCartItems(String email, int page, int size) {
        Users user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("사용자 이메일을 찾을 수 없습니다."));

        Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() -> new CartNotFoundException("장바구니가 존재하지 않습니다."));

        Pageable pageable = PageRequest.of(page, size);
        Page<CartItem> cartItemsPage = cartItemRepository.findByCartId(cart.getId(), pageable);
        Page<CartItemResponse> cartItemResponsesPage = cartItemsPage.map(CartItemResponse::toDto);
        return CartDto.toDto(cart, cartItemResponsesPage);
    }
}
