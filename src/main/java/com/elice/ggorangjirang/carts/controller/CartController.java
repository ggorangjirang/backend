package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartItemDto;
import com.elice.ggorangjirang.carts.dto.AddCartItemRequest;
import com.elice.ggorangjirang.carts.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartItemService cartItemService;

    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addCartItem(@PathVariable Long cartId, @RequestBody AddCartItemRequest addCartItemRequest) {
        CartItemDto cartItemDto = cartItemService.addCartItem(cartId, addCartItemRequest.getProductId(), addCartItemRequest.getQuantity());
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable Long cartId) {
        List<CartItemDto> cartItems = cartItemService.getCartItems(cartId);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItemDto cartItemDto = cartItemService.updateCartItem(cartItemId, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
