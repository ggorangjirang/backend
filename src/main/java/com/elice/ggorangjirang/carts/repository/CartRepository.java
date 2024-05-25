package com.elice.ggorangjirang.carts.repository;

import com.elice.ggorangjirang.carts.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
