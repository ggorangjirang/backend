package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.orders.entity.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemsResponse {

  private final int quantity;
  private final int orderPrice;
  private final String productName;
  private final String imageUrl;
  private final String description;
  private final int totalPrice;

  public OrderItemsResponse(OrderItem orderItem){
    this.quantity = orderItem.getQuantity();
    this.orderPrice = orderItem.getOrderPrice();
    this.productName = orderItem.getProduct().getName();
    this.imageUrl = orderItem.getProduct().getImageUrl();
    this.description = orderItem.getProduct().getDescription();
    this.totalPrice = orderItem.getTotalPrice();
  }
}
