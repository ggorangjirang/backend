package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderRequest {
  private DeliveryDto delivery;
  private List<OrderItemDTO> orderItems;
}
