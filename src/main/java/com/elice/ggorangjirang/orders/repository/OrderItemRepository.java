package com.elice.ggorangjirang.orders.repository;

import com.elice.ggorangjirang.orders.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
  @Query("SELECT COUNT(oi) > 0 FROM OrderItem oi WHERE oi.order.users.id = :userId AND oi.product.id = :productId")
  boolean existsByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
