package com.elice.ggorangjirang.websocket;

import com.elice.ggorangjirang.deliveries.dto.DeliveryStatusDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class DeliveryStatusController {

  @Autowired
  private DeliveryService deliveryService;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/updateAlarm")
  public void updateDeliveryStatus(DeliveryStatusDto statusDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new IllegalArgumentException("User not authenticated");
    }

    long orderId = statusDto.getId();
    String status = statusDto.getStatus();

    List<Deliveries> deliveriesList = deliveryRepository.findByOrderId(orderId);
    if (deliveriesList.isEmpty()) {
      throw new IllegalArgumentException("Invalid order ID: " + orderId);
    }

    Deliveries delivery = deliveriesList.get(0); // Assuming one delivery per order
    delivery.setStatus(status);

    if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
      delivery.setArrivalDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    } else if ("DELIVERING".equalsIgnoreCase(status)) {
      delivery.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    deliveryRepository.save(delivery);

    Order order = delivery.getOrder();
    if (order != null && order.getUsers() != null) {
      Long userId = order.getUsers().getId();
      String notificationMessage = getNotificationMessage(status);
      messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/updateDeliveryStatus", notificationMessage);
    }
  }

  private String getNotificationMessage(String status) {
    if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
      return "배달이 완료되었습니다.";
    } else if ("DELIVERING".equalsIgnoreCase(status)) {
      return "배달이 시작되었습니다.";
    }
    return "";
  }

  @MessageMapping("/bellAlarm")
  public void handleDeliveryCompleteNotification(DeliveryStatusDto statusDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new IllegalArgumentException("User not authenticated");
    }

    long orderId = statusDto.getId();
    String status = statusDto.getStatus();

    List<Deliveries> deliveriesList = deliveryRepository.findByOrderId(orderId);
    if (deliveriesList.isEmpty()) {
      throw new IllegalArgumentException("Invalid order ID: " + orderId);
    }

    Deliveries delivery = deliveriesList.get(0);
    delivery.setStatus(status);

    if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
      delivery.setArrivalDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    } else if ("DELIVERING".equalsIgnoreCase(status)) {
      delivery.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    deliveryRepository.save(delivery);

    Order order = delivery.getOrder();
    if (order != null && order.getUsers() != null) {
      Long userId = order.getUsers().getId();
      String notificationMessage = "";

      if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
        notificationMessage = createDeliveryCompleteNotificationMessage(order);
      } else if ("DELIVERING".equalsIgnoreCase(status)) {
        notificationMessage = createDeliveringNotificationMessage(order);
      }

      messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/bellDeliveryStatus", notificationMessage);
    }
  }

  private String createDeliveryCompleteNotificationMessage(Order order) {
    List<OrderItem> orderItems = order.getOrderItems();
    String firstItemName = orderItems.get(0).getProduct().getName();
    int remainingItemsCount = orderItems.size() - 1;

    return firstItemName + " 외 " + remainingItemsCount + "개의 상품이 배송 완료되었습니다.";
  }

  private String createDeliveringNotificationMessage(Order order) {
    List<OrderItem> orderItems = order.getOrderItems();
    String firstItemName = orderItems.get(0).getProduct().getName();
    return firstItemName + " 외 " + (orderItems.size() - 1) + "개의 상품이 배송 중입니다.";
  }
}