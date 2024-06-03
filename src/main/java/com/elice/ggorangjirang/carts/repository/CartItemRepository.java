package com.elice.ggorangjirang.carts.repository;

import com.elice.ggorangjirang.carts.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
    Page<CartItem> findByCartId(@Param("cartId") Long cartId, Pageable pageable);
}
