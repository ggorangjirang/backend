package com.elice.ggorangjirang.orders.repository;

import com.elice.ggorangjirang.orders.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
  List<Order> findByUserId(Long userId);
}
