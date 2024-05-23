package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSaveRequest {
  private Long userId; // 추후에 User 로 변경
  private List<OrderItemSaveRequest> orderItem = new ArrayList<>();

  // 추후 Delivery 도메인에서 가져오는 필드
  private Deliveries deliveries;

  public Order toEntity(List<OrderItem> orderItems) {
    return Order.builder()
        .userId(userId)
        .orderItems(orderItems)
        .deliveries(deliveries)
        .build();
  }

}
