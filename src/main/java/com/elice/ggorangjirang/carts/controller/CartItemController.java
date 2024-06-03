package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.dto.CartItemRequest;
import com.elice.ggorangjirang.carts.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItemResponse> addCartItem(@RequestBody CartItemRequest cartItemRequestDto) {
        CartItemResponse cartItemDto = cartItemService.addCartItem(
            cartItemRequestDto.getCartId(),
            cartItemRequestDto.getProductId(),
            cartItemRequestDto.getQuantity()
        );
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping
    public ResponseEntity<Page<CartItemResponse>> getCartItems(@RequestParam Long cartId, Pageable pageable) {
        Page<CartItemResponse> cartItems = cartItemService.getCartItemsByCartId(cartId, pageable);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemResponse> updateCartItem(@PathVariable Long id, @RequestParam int quantity) {
        CartItemResponse cartItemDto = cartItemService.updateCartItem(id, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
