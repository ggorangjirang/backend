package com.elice.ggorangjirang.deliveries.Mapper;

import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {



  public Deliveries toEntity(DeliveryDto deliveryDto) {
    Deliveries delivery = new Deliveries();
    delivery.setName(deliveryDto.getName());
    delivery.setPhoneNumber(deliveryDto.getPhoneNumber());
    delivery.setZipcode(deliveryDto.getZipcode());
    delivery.setStreetAddress(deliveryDto.getStreetAddress());
    delivery.setDetailAddress(deliveryDto.getDetailAddress());
    delivery.setRequest(deliveryDto.getRequest());
    delivery.setStatus("Pending");

    return delivery;
  }


  public DeliveryDto toDto(Deliveries delivery) {
    DeliveryDto deliveryDto = new DeliveryDto();
    deliveryDto.setName(delivery.getName());
    deliveryDto.setPhoneNumber(delivery.getPhoneNumber());
    deliveryDto.setZipcode(delivery.getZipcode());
    deliveryDto.setStreetAddress(delivery.getStreetAddress());
    deliveryDto.setDetailAddress(delivery.getDetailAddress());
    deliveryDto.setRequest(delivery.getRequest());
    // Set other fields as needed

    return deliveryDto;
  }

}
