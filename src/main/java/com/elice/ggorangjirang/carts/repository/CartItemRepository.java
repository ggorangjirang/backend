package com.elice.ggorangjirang.carts.repository;

import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.carts.entity.CartItem;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

//    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.user.id = :userId")
//    Page<CartItem> findByUserId(@Param("userId") Long userId, Pageable pageable);
}
