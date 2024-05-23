package com.elice.ggorangjirang.orders.repository;

import com.elice.ggorangjirang.orders.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
