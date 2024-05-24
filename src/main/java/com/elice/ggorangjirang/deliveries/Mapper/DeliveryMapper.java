package com.elice.ggorangjirang.deliveries.Mapper;

import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import java.time.LocalDate;
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
    delivery.setArrivalDate(LocalDate.now().plusDays(3)); // 예시로 3일 후 도착
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
    return deliveryDto;
  }

}