package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.orders.entity.Order;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Getter
public class OrderPageableResponse {
  private final Long id;
  private final LocalDateTime orderDate;
  private final String orderStatus;
  private final String orderNumber;
  private final String deliveryStatus;
  private final int totalAllPrice;
  private final Page<OrderItemsResponse> orderItems;

  public OrderPageableResponse(Order order, Pageable pageable) {
    this.id = order.getId();
    this.orderDate = order.getOrderDate();
    this.orderStatus = order.getOrderStatus().name();
    this.orderNumber = order.getOrderNumber();
    this.deliveryStatus = order.getDeliveries().getStatus();
    this.totalAllPrice = order.getTotalAllPrice();

    List<OrderItemsResponse> items = order.getOrderItems()
        .stream()
        .map(OrderItemsResponse::new)
        .toList();
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), items.size());
    this.orderItems = new PageImpl<>(items.subList(start, end), pageable, items.size());
  }
}
