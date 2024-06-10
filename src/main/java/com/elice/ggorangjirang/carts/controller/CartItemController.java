package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.dto.CartItemRequest;
import com.elice.ggorangjirang.carts.service.CartItemService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CartItemResponse> addCartItem(@RequestBody CartItemRequest cartItemRequestDto) {
        String email = getAuthenticatedEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Users user = userService.findByUsername(email);

        CartItemResponse cartItemDto = cartItemService.addCartItem(
            user.getCart().getId(),
            cartItemRequestDto.getProductId(),
            cartItemRequestDto.getQuantity()
        );
        return ResponseEntity.ok(cartItemDto);
    }

    @GetMapping
    public ResponseEntity<Page<CartItemResponse>> getCartItems(@RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "size", defaultValue = "5") int size) {
        String email = getAuthenticatedEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Users user = userService.findByUsername(email);
        Long cartId = user.getCart().getId();

        Page<CartItemResponse> cartItems = cartItemService.getCartItemsByCartId(cartId, page, size);
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/array")
    public ResponseEntity<List<CartItemResponse>> getCartItemList() {
        String email = getAuthenticatedEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Users user = userService.findByUsername(email);
        Long cartId = user.getCart().getId();

        List<CartItemResponse> cartItems = cartItemService.getCartItemListByCartId(cartId);
        return ResponseEntity.ok(cartItems);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CartItemResponse> updateCartItem(@PathVariable Long id,
                                                           @RequestParam(name = "quantity") int quantity) {
        String email = getAuthenticatedEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        CartItemResponse cartItemDto = cartItemService.updateCartItem(id, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        String email = getAuthenticatedEmail();
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }

    private String getAuthenticatedEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            return (String) principal;
        }

        return null;
    }
}
