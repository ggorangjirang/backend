package com.elice.ggorangjirang.orders.dto;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDeliverySaveRequest {
  private String zipcode;
  private String streetAddress;
  private String detailAddress;

  public Deliveries toEntity(){
    return Deliveries.builder()
        .zipcode(zipcode)
        .streetAddress(streetAddress)
        .detailAddress(detailAddress)
        .build();
  }
}
