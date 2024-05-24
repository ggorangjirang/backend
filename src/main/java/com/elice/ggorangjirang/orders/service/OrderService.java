package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final DeliveryRepository deliveryRepository;

  // User 엔티티 생성후 변경
  @Transactional
  public Order create(Long userId, Deliveries delivery, List<OrderItem> orderItems) {
    Order createdOrder = Order.createOrder(userId, delivery, orderItems);

    return orderRepository.save(createdOrder);
  }

}
