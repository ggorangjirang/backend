package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
//    public ResponseEntity<CartDto> createCart(@RequestParam Long userId) {
//        CartDto cartDto = cartService.createCart(userId);
//        return ResponseEntity.ok(cartDto);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long id) {
        CartDto cartDto = cartService.getCartById(id);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
