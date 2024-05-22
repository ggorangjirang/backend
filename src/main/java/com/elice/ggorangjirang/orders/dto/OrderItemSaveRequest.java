package com.elice.ggorangjirang.orders.dto;


import com.elice.ggorangjirang.orders.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemSaveRequest {

  // 추후 가져오기
  private Long productId;

  private Integer price;

  private Integer quantity;


  public OrderItem toEntity(){
    return OrderItem.builder()
        .productId(productId)
        .price(price)
        .quantity(quantity)
        .build();
  }
}
