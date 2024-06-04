package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartItemsByUserId(@PathVariable Long userId,
        @PageableDefault(page = 0, size = 16) Pageable pageable) {
        CartDto cartDto = cartService.getCartItems(userId, pageable);
        return ResponseEntity.ok(cartDto);
    }
}
