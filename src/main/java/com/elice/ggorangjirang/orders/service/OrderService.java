package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final DeliveryRepository deliveryRepository;
  private final UserRepository userRepository;

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

  @Transactional
  public void deleteByUserIdAndOrderId(String email, Long orderId) {

    Users user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    Long userId = user.getId();

    Order order = orderRepository.findByIdAndUsers_Id(orderId, userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. Order ID: " + orderId + ", User ID: " + userId));

    if(order.getDeliveries().getStatus().equals("DELIVERING")) {
      throw new IllegalStateException("배송 중이라 취소가 불가능합니다.");
    } else if(order.getDeliveries().getStatus().equals("DELIVERY_COMPLETE")) {
      throw new IllegalStateException("배송 완료라 취소가 불가능합니다.");
    }

    for(OrderItem orderItem : order.getOrderItems()) {
      orderItem.delete();
    }

    orderRepository.deleteById(orderId);
  }


  // 주문 삭제
  @Transactional
  public void deleteById(Long id){
    Order order = findById(id);

    if(order.getDeliveries().getStatus().equals("DELIVERING")){
      throw new IllegalStateException("배송중이라 취소가 불가능합니다.");
    }else if(order.getDeliveries().getStatus().equals("DELIVERY_COMPLETE")){
      throw new IllegalStateException("배송완료라 취소가 불가능합니다.");
    }

    for(OrderItem orderItem : order.getOrderItems()){
      orderItem.delete();
    }

    orderRepository.deleteById(id);
  }

  // 모든 주문 조회
  public List<Order> findAll(){
    return orderRepository.findAll();
  }



}
