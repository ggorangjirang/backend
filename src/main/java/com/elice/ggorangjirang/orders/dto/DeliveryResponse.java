package com.elice.ggorangjirang.orders.dto;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.users.entity.Address;
import lombok.Getter;

@Getter
public class DeliveryResponse {
  private final String zipcode;
  private final String streetAddress;
  private final String detailAddress;
  private final String name;

  public DeliveryResponse(Deliveries deliveries){
    this.zipcode = deliveries.getZipcode();
    this.streetAddress = deliveries.getStreetAddress();
    this.detailAddress = deliveries.getDetailAddress();
    this.name = deliveries.getName();
  }

}
