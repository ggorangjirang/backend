package com.elice.ggorangjirang.orders.repository;

import com.elice.ggorangjirang.orders.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  // 추후 userId 매핑 후 변경
  List<Order> findAllByUsers_Id(Long userId);

  Optional<Order> findByIdAndUsers_Id(Long orderId, Long userId);
}
