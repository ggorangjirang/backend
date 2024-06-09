package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartItemResponse addCartItem(Long cartId, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException("유효하지 않은 상품 ID입니다."));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
            .orElseGet(() -> CartItem.builder()
                .cart(cartRepository.findById(cartId)
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 장바구니 ID입니다.")))
                .product(product)
                .quantity(quantity)
                .build());

        cartItem = cartItemRepository.save(cartItem);

        return CartItemResponse.toDto(cartItem);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CartItemResponse> getCartItemsByCartId(Long cartId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CartItem> cartItemsPage = cartItemRepository.findByCartId(cartId, pageable);
        return cartItemsPage.map(CartItemResponse::toDto);
    }

    @Override
    public CartItemResponse updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 장바구니 아이템 ID입니다."));

        cartItem.setQuantity(quantity);
        cartItem = cartItemRepository.save(cartItem);

        return CartItemResponse.toDto(cartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new IllegalArgumentException("유효하지 않은 장바구니 아이템 ID입니다.");
        }
        cartItemRepository.deleteById(cartItemId);
    }
}
