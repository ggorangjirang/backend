package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.orders.entity.Order;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
  private Long orderId;
  private LocalDateTime orderDate;
  private String orderStatus;
  private double totalAmount;
  private Long userId;
  private List<OrderItemResponse> orderItems;

  public static OrderResponse fromEntity(Order order) {
    List<OrderItemResponse> orderItems = order.getOrderItems().stream()
        .map(OrderItemResponse::fromEntity)
        .collect(Collectors.toList());

    OrderResponse response = new OrderResponse();
    response.setOrderId(order.getId());
    response.setOrderDate(order.getOrderDate());
    response.setOrderStatus(order.getStatus());
    response.setTotalAmount(order.getTotalAmount());
    response.setUserId(order.getUserId());
    response.setOrderItems(orderItems);

    return response;
  }
}
