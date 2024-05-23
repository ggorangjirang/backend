package com.elice.ggorangjirang.cartitems.controller;

import com.elice.ggorangjirang.cartitems.dto.CartItemDto;
import com.elice.ggorangjirang.cartitems.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addCartItem(@PathVariable Long userId, @RequestBody CartItemDto cartItemDto) {
        cartItemService.addCartItem(userId, cartItemDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/increase/{cartItemId}")
    public ResponseEntity<CartItemDto> increaseCartItemQuantity(@PathVariable Long cartItemId) {
        CartItemDto updatedCartItem = cartItemService.increaseCartItemQuantity(cartItemId);
        return ResponseEntity.ok(updatedCartItem);
    }

    @PostMapping("/decrease/{cartItemId}")
    public ResponseEntity<CartItemDto> decreaseCartItemQuantity(@PathVariable Long cartItemId) {
        CartItemDto updatedCartItem = cartItemService.decreaseCartItemQuantity(cartItemId);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().build();
    }
}
