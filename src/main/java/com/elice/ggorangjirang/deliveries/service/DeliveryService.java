package com.elice.ggorangjirang.deliveries.service;

import com.elice.ggorangjirang.deliveries.Mapper.DeliveryMapper;
import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

  private final DeliveryMapper deliveryMapper;
  private final DeliveryRepository deliveryRepository;
  private final OrderRepository orderRepository;

  @Transactional
  public Order getOrderWithItems(Long orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));
    // 강제로 초기화
    order.getOrderItems().forEach(orderItem -> {
      Hibernate.initialize(orderItem.getProduct());
    });
    return order;
  }

  @Transactional
  public Long addDelivery(DeliveryDto deliveryDto) {
    Deliveries delivery = deliveryMapper.toEntity(deliveryDto);
    Deliveries savedDelivery = deliveryRepository.save(delivery);
    return savedDelivery.getId();
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
    if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
      LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
      delivery.setArrivalDate(now);
    } else if("DELIVERING".equalsIgnoreCase(status)){
      LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
      delivery.setStartDate(now);
    }
    return deliveryRepository.save(delivery);
  }

  @Transactional
  public List<Deliveries> getDeliveriesByOrderId(long orderId) {
    return deliveryRepository.findByOrderId(orderId);
  }
}