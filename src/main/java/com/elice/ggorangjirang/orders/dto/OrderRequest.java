package com.elice.ggorangjirang.orders.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderRequest {

  private Long deliveryId;
  private List<Long> orderItemId;
}
