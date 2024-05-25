package com.elice.ggorangjirang.deliveries.Mapper;

import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.orders.entity.Order;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {



  public Deliveries toEntity(DeliveryDto deliveryDto, Order order) {
    Deliveries delivery = new Deliveries();
    delivery.setName(deliveryDto.getName());
    delivery.setPhoneNumber(deliveryDto.getPhoneNumber());
    delivery.setZipcode(deliveryDto.getZipcode());
    delivery.setStreetAddress(deliveryDto.getStreetAddress());
    delivery.setDetailAddress(deliveryDto.getDetailAddress());
    delivery.setRequest(deliveryDto.getRequest());
    delivery.setStatus("Pending");
    delivery.setArrivalDate(LocalDate.now().plusDays(3)); // 예시로 3일 후 도착

    delivery.setOrder(order);

    return delivery;
  }


}
