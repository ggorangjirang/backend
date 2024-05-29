package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartItemDto;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartItemDto addCartItem(Long cartId, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 상품 ID입니다."));

        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 장바구니 ID입니다."));

        CartItem cartItem = CartItem.builder()
            .cart(cart)
            .product(product)
            .quantity(quantity)
            .build();

        cartItem = cartItemRepository.save(cartItem);

        return CartItemDto.toDto(cartItem);
    }

    @Override
    public List<CartItemDto> getCartItems(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        return cartItems.stream()
            .map(CartItemDto::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public Page<CartItemDto> getCartItemsByCartId(Long cartId, Pageable pageable) {
        Page<CartItem> cartItemsPage = cartItemRepository.findByCartId(cartId, pageable);
        return cartItemsPage.map(CartItemDto::toDto);
    }

    @Override
    public CartItemDto updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 장바구니 아이템 ID입니다."));

        cartItem.setQuantity(quantity);
        cartItem = cartItemRepository.save(cartItem);

        return CartItemDto.toDto(cartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new IllegalArgumentException("유효하지 않은 장바구니 아이템 ID입니다.");
        }
        cartItemRepository.deleteById(cartItemId);
    }
}
