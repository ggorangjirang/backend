package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.carts.repository.CartItemRepository;
import com.elice.ggorangjirang.carts.repository.CartRepository;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.discord.DiscordWebhook;
import com.elice.ggorangjirang.global.exception.hierachy.common.OrderCannotCancelDeliveredException;
import com.elice.ggorangjirang.global.exception.hierachy.common.OrderCannotCancelDeliveringException;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.elice.ggorangjirang.global.constant.GlobalConstants.*;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final DeliveryRepository deliveryRepository;
  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final DiscordWebhook discordWebhook;

  // User 엔티티 생성후 변경
  @Transactional
  public Order create(Users users, Deliveries delivery, List<OrderItem> orderItems) {
    Order createdOrder = Order.createOrder(users, delivery, orderItems);
    Order savedOrder = orderRepository.save(createdOrder);

    // 주문 생성 후 장바구니 아이템 삭제
    Long cartId = cartRepository.findByUser_Id(users.getId()).getId();
    List<Long> productIds = orderItems.stream()
        .map(orderItem -> orderItem.getProduct().getId())
        .toList();
    cartItemRepository.deleteByCartIdAndProductIdIn(cartId, productIds);

    discordWebhook.sendInfoMessage(NEW_ORDER_NOTICE + " (ID: " + savedOrder.getOrderNumber() + ")");

    // 재고량 확인 및 알림
    orderItems.forEach(orderItem -> {
      if (orderItem.getProduct().getStock() < 5) {
        String productName = orderItem.getProduct().getName();
        discordWebhook.sendWarningMessage("Product " + productName + PRODUCT_STOCK_NOTICE);
      }
    });

    return savedOrder;
  }

  // 회원 별 조회
  public Order findById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("not found orderId  : " + id));
  }

  // 모든 회원 조회
  public Page<Order> findAllByUserId(Long userId, Pageable pageable){
    return orderRepository.findAllByUsers_Id(userId,pageable);
  }

  @Transactional
  public Order cancelOrder(Long userId, Long orderId) {
    Order order = orderRepository.findByIdAndUsers_Id(orderId, userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. Order ID: " + orderId + ", User ID: " + userId));


    String deliveryStatus = order.getDeliveries().getStatus();
    if ("DELIVERING".equals(deliveryStatus)) {
      throw new OrderCannotCancelDeliveringException();
    } else if ("DELIVERY_COMPLETE".equals(deliveryStatus)) {
      throw new OrderCannotCancelDeliveredException();
    }

    for(OrderItem orderItem : order.getOrderItems()) {
      orderItem.delete();
    }


    Deliveries deliveries = order.getDeliveries();
    deliveries.setStatus("DELIVERY_CANCEL");

    order.cancel();

    return order;
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
