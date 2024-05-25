package com.elice.ggorangjirang.cartitems.controller;

import com.elice.ggorangjirang.cartitems.dto.CartItemDto;
import com.elice.ggorangjirang.cartitems.service.CartItemService;
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
    public ResponseEntity<CartItemDto> addCartItem(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        CartItemDto cartItemDto = cartItemService.addCartItem(cartId, productId, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getCartItems(@RequestParam Long cartId) {
        List<CartItemDto> cartItems = cartItemService.getCartItems(cartId);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItemDto cartItemDto = cartItemService.updateCartItem(cartItemId, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
