package com.elice.ggorangjirang.deliveries.service;

import com.elice.ggorangjirang.deliveries.Mapper.DeliveryMapper;
import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

  private final DeliveryRepository deliveryRepository;
  private final DeliveryMapper deliveryMapper;

  @Transactional
  public void addDelivery(DeliveryDto deliveryDto) {
    Deliveries delivery = deliveryMapper.toEntity(deliveryDto);
    deliveryRepository.save(delivery);
  }

  @Transactional
  public Deliveries getDeliveryById(long id) {
    return deliveryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid delivery ID: " + id));
  }

  @Transactional
  public Deliveries updateDeliveryStatus(long id, String status) {
    Deliveries delivery = getDeliveryById(id);
    delivery.setStatus(status);
    return deliveryRepository.save(delivery);
  }

  @Transactional
  public List<Deliveries> getDeliveriesByOrderId(long orderId) {
    return deliveryRepository.findByOrderId(orderId);
  }

}