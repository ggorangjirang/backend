package com.elice.ggorangjirang.carts.service;

import com.elice.ggorangjirang.carts.dto.CartDto;
import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.repository.CartRepository;
//import com.elice.ggorangjirang.users.entity.Users;
//import com.elice.ggorangjirang.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
//    private final UsersRepository usersRepository;

//    @Transactional
//    public CartDto createCart(Long userId) {
//        Users user = usersRepository.findById(userId)
//            .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
//
//        Cart cart = Cart.builder()
//            .user(user)
//            .build();
//
//        Cart savedCart = cartRepository.save(cart);
//        return CartDto.toDto(savedCart);
//    }

    @Transactional(readOnly = true)
    public CartDto getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.map(CartDto::toDto).orElse(null);
    }

    @Transactional
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
