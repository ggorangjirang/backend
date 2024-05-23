package com.elice.ggorangjirang.cartitems.service;

import com.elice.ggorangjirang.cartitems.dto.CartItemDto;
import com.elice.ggorangjirang.cartitems.entity.CartItem;
import com.elice.ggorangjirang.cartitems.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
//import com.elice.ggorangjirang.users.entity.Users;
//import com.elice.ggorangjirang.users.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
//    private final UsersRepository userRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository, CartRepository cartRepository, UsersRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
//        this.userRepository = userRepository;
    }

//    public void addCartItem(Long userId, CartItemDto cartItemDto) {
//        Users user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//        Cart cart = user.getCart();
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUser(user);
//            cart = cartRepository.save(cart);
//            user.setCart(cart);
//            userRepository.save(user);
//        }

//        Product product = productRepository.findById(cartItemDto.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));
//
//        CartItem cartItem = CartItem.builder()
//            .cart(cart)
//            .product(product)
//            .quantity(cartItemDto.getQuantity())
//            .build();
//        cartItemRepository.save(cartItem);
//    }

    public CartItemDto increaseCartItemQuantity(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("CartItem not found"));
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return CartItemDto.fromEntity(updatedCartItem);
    }

    public CartItemDto decreaseCartItemQuantity(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("CartItem not found"));
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            CartItem updatedCartItem = cartItemRepository.save(cartItem);
            return CartItemDto.fromEntity(updatedCartItem);
        } else {
            throw new IllegalArgumentException("Cannot decrease quantity below 1");
        }
    }

    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
