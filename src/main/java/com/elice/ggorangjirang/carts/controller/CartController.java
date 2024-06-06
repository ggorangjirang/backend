package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.service.CartService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<CartDto> getCartItemsByUserId(@RequestHeader("Authorization") String token,
                                                        @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = emailOptional.get();
        CartDto cartDto = cartService.getCartItems(email, pageable);
        return ResponseEntity.ok(cartDto);
    }
}
