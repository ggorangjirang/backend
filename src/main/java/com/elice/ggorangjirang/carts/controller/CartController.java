package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // 카트 항목 추가
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody CartDto cartDto) {
        cartService.addCart(cartDto);
        return ResponseEntity.ok().build();
    }

    // 사용자 ID로 카트 항목 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDto>> getCartItemsByUserId(@PathVariable Long userId) {
        List<CartDto> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    // 여러 개의 카트 항목 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteCartItems(@RequestBody List<Long> itemIds) {
        cartService.deleteCartItems(itemIds);
        return ResponseEntity.ok().build();
    }

    // 특정 카트 항목의 수량 증가
    @PostMapping("/increase/{itemId}")
    public ResponseEntity<CartDto> increaseItemCount(@Path
