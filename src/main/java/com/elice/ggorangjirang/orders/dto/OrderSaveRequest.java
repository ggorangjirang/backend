package com.elice.ggorangjirang.orders.dto;


import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderSaveRequest {
  private Long userId; // 추후에 User 로 변경
  private List<OrderItemSaveRequest> orderItem = new ArrayList<>();

  // 추후 Delivery 도메인에서 가져오는 필드
  private OrderDeliverySaveRequest deliveries;

  public Order toEntity(List<OrderItem> orderItems) {
    return Order.builder()
        .userId(userId)
        .orderItems(orderItems == null ? new ArrayList<>() : orderItems)
        .deliveries(deliveries.toEntity())
        .build();
  }

  public List<OrderItemSaveRequest> getOrderItem() {
    if (this.orderItem == null) {
      throw new IllegalStateException("Order item list is not initialized");
    }
    return this.orderItem;
  }
}
