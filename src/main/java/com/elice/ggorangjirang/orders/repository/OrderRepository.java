package com.elice.ggorangjirang.orders.repository;

import com.elice.ggorangjirang.orders.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  // 추후 userId 매핑 후 변경
  List<Order> findAllByUserId(Long userId);
}
