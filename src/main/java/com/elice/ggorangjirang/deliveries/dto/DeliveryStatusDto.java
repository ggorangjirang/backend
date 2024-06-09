package com.elice.ggorangjirang.deliveries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStatusDto {

  private Long orderId;

  private String status;

}
