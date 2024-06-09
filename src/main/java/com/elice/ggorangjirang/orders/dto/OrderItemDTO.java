package com.elice.ggorangjirang.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItemDTO {
  private Long productId;
  private int quantity;
}
