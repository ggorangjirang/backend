package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.dto.CartItemRequest;
import com.elice.ggorangjirang.carts.service.CartItemService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CartItemResponse> addCartItem(@RequestBody CartItemRequest cartItemRequestDto,
                                                        @RequestHeader("Authorization") String token) {

        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = emailOptional.get();
        Users user = userService.findByUsername(email);

        CartItemResponse cartItemDto = cartItemService.addCartItem(
            user.getCart().getId(),
            cartItemRequestDto.getProductId(),
            cartItemRequestDto.getQuantity()
        );
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping
    public ResponseEntity<Page<CartItemResponse>> getCartItems(@RequestParam Long cartId,
        @PageableDefault(size = 16) Pageable pageable) {
        Page<CartItemResponse> cartItems = cartItemService.getCartItemsByCartId(cartId, pageable);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemResponse> updateCartItem(@PathVariable Long id,
                                                           @RequestParam(name = "quantity") int quantity,
                                                           @RequestHeader("Authorization") String token) {
        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CartItemResponse cartItemDto = cartItemService.updateCartItem(id, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id,
                                               @RequestHeader("Authorization") String token) {
        Optional<String> emailOptional = jwtService.extractEmail(token);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
