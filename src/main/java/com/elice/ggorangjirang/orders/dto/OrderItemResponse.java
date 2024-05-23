package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.orders.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
  private Long orderItemId;
  private Long productId;
  private String productName;
  private int quantity;
  private double price;

  public static OrderItemResponse fromEntity(OrderItem orderItem) {
    OrderItemResponse response = new OrderItemResponse();
    response.setOrderItemId(orderItem.getId());
    response.setProductId(orderItem.getProduct().getId());
    response.setProductName(orderItem.getProduct().getName());
    response.setQuantity(orderItem.getQuantity());
    response.setPrice(orderItem.getPrice());

    return response;
  }
}
