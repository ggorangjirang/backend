package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.orders.entity.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderResponse {
  private final Long id;
  private final LocalDateTime orderDate;
  private final String orderStatus;
  private final String orderNumber;
  private final String deliveryStatus;
  private final int totalAllPrice;
  private final List<OrderItemResponse> orderItems;


  public OrderResponse(Order order){
    this.id = order.getId();
    this.orderItems = order.getOrderItems()
        .stream()
        .map(OrderItemResponse::new)
        .toList();
    this.deliveryStatus = order.getDeliveries().getStatus();
    this.orderDate = order.getOrderDate();
    this.orderStatus = order.getOrderStatus().name();
    this.orderNumber = order.getOrderNumber();
    this.totalAllPrice = order.getTotalAllPrice();
  }
}
