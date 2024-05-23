package com.elice.ggorangjirang.cartitems.repository;

import com.elice.ggorangjirang.cartitems.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
