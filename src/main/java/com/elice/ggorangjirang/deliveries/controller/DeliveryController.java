package com.elice.ggorangjirang.deliveries.controller;

import static com.elice.ggorangjirang.global.constant.GlobalConstants.DELIVERY_COMPLETE_EMAIL_CONTENT;
import static com.elice.ggorangjirang.global.constant.GlobalConstants.DELIVERY_COMPLETE_EMAIL_TITLE;

import com.elice.ggorangjirang.deliveries.dto.DeliveryDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.deliveries.dto.DeliveryStatusDto;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.global.email.service.EmailService;
import com.elice.ggorangjirang.orders.entity.Order;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

  private final DeliveryService deliveryService;
  private final DeliveryRepository deliveryRepository;
  private final EmailService emailService;
  private final UserRepository userRepository;

  @PostMapping("/api/deliveries")
  public ResponseEntity<Long> addDelivery(@RequestBody DeliveryDto deliveryDto) {
    Long deliveryId = deliveryService.addDelivery(deliveryDto);
    return ResponseEntity.ok(deliveryId);
  }

  @GetMapping("/api/deliveries/{id}")
  public ResponseEntity<Deliveries> viewDelivery(@PathVariable("id") long id) {
    Deliveries delivery = deliveryService.getDeliveryById(id);
    return ResponseEntity.ok(delivery);
  }

  @GetMapping("/admin/orders/{orderId}/deliveries")
  public ResponseEntity<List<Deliveries>> viewDeliveryByOrderId(@PathVariable("orderId") long orderId) {
    List<Deliveries> deliveriesList = deliveryService.getDeliveriesByOrderId(orderId);
    return ResponseEntity.ok(deliveriesList);
  }

  @PatchMapping("/admin/deliveries/order/{orderId}/status")
  public ResponseEntity<String> updateDeliveriesStatusByOrderId(@PathVariable("orderId") long orderId, @RequestBody DeliveryStatusDto statusDto) {
    List<Deliveries> deliveriesList = deliveryRepository.findByOrderId(orderId);
    if (deliveriesList.isEmpty()) {
      throw new IllegalArgumentException("Invalid order ID: " + orderId);
    }

    Deliveries delivery = deliveriesList.get(0);
    delivery.setStatus(statusDto.getStatus());

    if ("DELIVERY_COMPLETE".equalsIgnoreCase(statusDto.getStatus())) {
      delivery.setArrivalDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

      Order order = delivery.getOrder();
      Users user = order.getUsers();

      if (order == null) {
        throw new IllegalStateException("Order not found for delivery ID: " + delivery.getId());
      }
      String orderNumber = order.getOrderNumber();

      String email = user.getEmail();
      String title = DELIVERY_COMPLETE_EMAIL_TITLE;
      String content = DELIVERY_COMPLETE_EMAIL_CONTENT + orderNumber;
      emailService.sendSimpleMessage(email, title, content);
    } else if ("DELIVERING".equalsIgnoreCase(statusDto.getStatus())) {
      delivery.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    deliveryRepository.save(delivery);

    return ResponseEntity.ok("배송 상태 변경.");
  }
}