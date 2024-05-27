package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.elice.ggorangjirang.users.entity.Users;
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
  public Order create(Users users, Deliveries delivery, List<OrderItem> orderItems) {
    Order createdOrder = Order.createOrder(users, delivery, orderItems);

    return orderRepository.save(createdOrder);
  }

  // 회원 별 조회
  public Order findById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("not found orderId  : " + id));
  }

  // 모든 회원 조회
  public List<Order> findAllByUserId(Long userId){
    return orderRepository.findAllByUsers_Id(userId);
  }

  // 주문 삭제
  public void deleteById(Long id){
    orderRepository.deleteById(id);
  }

}
