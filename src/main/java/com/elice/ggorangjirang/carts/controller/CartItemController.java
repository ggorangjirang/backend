package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartItemDto;
import com.elice.ggorangjirang.carts.dto.CartItemRequest;
import com.elice.ggorangjirang.carts.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemRequest cartItemRequestDto) {
        CartItemDto cartItemDto = cartItemService.addCartItem(
            cartItemRequestDto.getUserId(),
            cartItemRequestDto.getProductId(),
            cartItemRequestDto.getQuantity()
        );
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getCartItems(@RequestParam Long userId) {
        List<CartItemDto> cartItems = cartItemService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long id, @RequestParam int quantity) {
        CartItemDto cartItemDto = cartItemService.updateCartItem(id, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
