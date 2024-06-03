package com.elice.ggorangjirang.carts.controller;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.dto.CartItemResponse;
import com.elice.ggorangjirang.carts.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/cart/add")
    public String addProductToCart(@RequestParam("productId") Long productId,
        @RequestParam("count") int count,
        RedirectAttributes redirectAttributes) {
        cartService.addCart(productId, count);
        redirectAttributes.addFlashAttribute("message", "상품이 장바구니에 추가되었습니다.");
        return "redirect:/products/" + productId;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartItemResponse> cartItems = cartService.viewCart();
        int totalPrice = cartItems.stream().mapToInt(cartItem -> cartItem.getPrice() * cartItem.getQuantity()).sum();
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartItemList", cartItems);
        return "cart/cart";
    }
}
