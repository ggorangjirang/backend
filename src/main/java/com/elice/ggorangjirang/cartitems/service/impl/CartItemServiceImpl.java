package com.elice.ggorangjirang.cartitems.service.impl;

import com.elice.ggorangjirang.cartitems.dto.CartItemDto;
import com.elice.ggorangjirang.cartitems.entity.CartItem;
import com.elice.ggorangjirang.cartitems.repository.CartItemRepository;
import com.elice.ggorangjirang.cartitems.service.CartItemService;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Override
    @Transactional
    public CartItemDto addCartItem(Long cartId, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new IllegalArgumentException("해당 카트를 찾을 수 없습니다."));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        return CartItemDto.toDto(savedCartItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> getCartItems(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        return cartItems.stream()
            .map(CartItemDto::toDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartItemDto updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new IllegalArgumentException("해당 카트 아이템을 찾을 수 없습니다."));

        cartItem.setQuantity(quantity);

        return CartItemDto.toDto(cartItem);
    }

    @Override
    @Transactional
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
