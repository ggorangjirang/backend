package com.elice.ggorangjirang.cartitems.controller;

import com.elice.ggorangjirang.cartitems.dto.CartItemDto;
import com.elice.ggorangjirang.cartitems.dto.CartItemRequestDto;
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
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemRequestDto cartItemRequestDto) {
        CartItemDto cartItemDto = cartItemService.addCartItem(
            cartItemRequestDto.getCartId(),
            cartItemRequestDto.getProductId(),
            cartItemRequestDto.getQuantity()
        );
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getCartItems(@RequestParam Long cartId) {
        List<CartItemDto> cartItems = cartItemService.getCartItems(cartId);
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
