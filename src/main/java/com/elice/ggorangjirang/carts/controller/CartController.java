package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.service.CartService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
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
                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = emailOptional.get();
        CartDto cartDto = cartService.getCartItems(email, page, size);
        return ResponseEntity.ok(cartDto);
    }
}
