package com.elice.ggorangjirang.carts.repository;

import com.elice.ggorangjirang.carts.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
