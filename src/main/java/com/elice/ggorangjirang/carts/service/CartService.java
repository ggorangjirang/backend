package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("유저가 인증되지 않았습니다.");
        }
        return Long.parseLong(authentication.getName());
    }

    public void addCart(Long productId, int quantity) {
        Long userId = getAuthenticatedUserId();
        Users user = userService.findById(userId);
        Cart cart = cartRepository.findByUserId(user.getId())
            .orElseGet(() -> {
                Cart newCart = Cart.builder()
                    .user(user)
                    .build();
                return cartRepository.save(newCart);
            });

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException("유효하지 않은 상품 ID입니다."));

        Optional<CartItem> existingCartItemOptional = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        if (existingCartItemOptional.isPresent()) {
            CartItem existingCartItem = existingCartItemOptional.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();
            cartItemRepository.save(cartItem);
        }
    }

    @Transactional(readOnly = true)
    public List<CartItemResponse> viewCart() {
        Long userId = getAuthenticatedUserId();
        Users user = userService.findById(userId);
        Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("장바구니가 존재하지 않습니다."));
        return cart.getCartItems().stream()
            .map(CartItemResponse::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CartDto getCartByUserId(Long userId) {
        Users user = userService.findById(userId);
        Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));
        return CartDto.toDto(cart);
    }
}
