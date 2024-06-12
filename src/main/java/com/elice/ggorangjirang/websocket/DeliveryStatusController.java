package com.elice.ggorangjirang.websocket;

import com.elice.ggorangjirang.deliveries.dto.DeliveryStatusDto;
import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.entity.DeliveryStatus; // 추가
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.deliveries.repository.DeliveryRepository;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    if (statusDto.getOrderId() == null) {
      throw new IllegalArgumentException("Order ID is required");
    }

    long orderId = statusDto.getOrderId();
    DeliveryStatus status = DeliveryStatus.valueOf(statusDto.getStatus().toUpperCase());

    List<Deliveries> deliveriesList = deliveryRepository.findByOrderId(orderId);
    if (deliveriesList.isEmpty()) {
      throw new IllegalArgumentException("Invalid order ID: " + orderId);
    }

    Deliveries delivery = deliveriesList.get(0);
    delivery.setStatus(status.name());

    if (status == DeliveryStatus.DELIVERY_COMPLETE) {
      delivery.setArrivalDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    } else if (status == DeliveryStatus.DELIVERING) {
      delivery.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    deliveryRepository.save(delivery);

    Order order = deliveryService.getOrderWithItems(orderId);
    if (order != null && order.getUsers() != null) {
      Long userId = order.getUsers().getId();
      String notificationMessage = getNotificationMessage(status);
      messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/updateDeliveryStatus", notificationMessage);
    }
  }

  private String getNotificationMessage(DeliveryStatus status) {
    if (status == DeliveryStatus.DELIVERY_COMPLETE) {
      return "배달이 완료되었습니다.";
    } else if (status == DeliveryStatus.DELIVERING) {
      return "배달이 시작되었습니다.";
    }
    return "배달준비";
  }

  @MessageMapping("/bellAlarm")
  public void handleDeliveryCompleteNotification(DeliveryStatusDto statusDto) {
    if (statusDto.getOrderId() == null) {
      throw new IllegalArgumentException("Order ID is required");
    }

    long orderId = statusDto.getOrderId();
    DeliveryStatus status = DeliveryStatus.valueOf(statusDto.getStatus().toUpperCase());

    List<Deliveries> deliveriesList = deliveryRepository.findByOrderId(orderId);
    if (deliveriesList.isEmpty()) {
      throw new IllegalArgumentException("Invalid order ID: " + orderId);
    }

    Deliveries delivery = deliveriesList.get(0);
    delivery.setStatus(status.name());

    if (status == DeliveryStatus.DELIVERY_COMPLETE) {
      delivery.setArrivalDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    } else if (status == DeliveryStatus.DELIVERING) {
      delivery.setStartDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    deliveryRepository.save(delivery);

    Order order = deliveryService.getOrderWithItems(orderId);
    if (order != null && order.getUsers() != null) {
      Long userId = order.getUsers().getId();
      String notificationMessage = "";

      if (status == DeliveryStatus.DELIVERY_COMPLETE) {
        notificationMessage = createDeliveryCompleteNotificationMessage(order);
      } else if (status == DeliveryStatus.DELIVERING) {
        notificationMessage = createDeliveringNotificationMessage(order);
      }

      if (status != DeliveryStatus.DELIVERY_READY) {
        messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/bellDeliveryStatus", notificationMessage);
      }
    }
  }

  private String createDeliveryCompleteNotificationMessage(Order order) {
    List<OrderItem> orderItems = order.getOrderItems();
    String firstItemName = orderItems.get(0).getProduct().getName();
    int remainingItemsCount = orderItems.size() - 1;

    if (orderItems.size() == 1) {
      return firstItemName + " 상품이 배송 완료되었습니다.";
    }

    return firstItemName + " 외 " + remainingItemsCount + "개의 상품이 배송 완료되었습니다.";
  }

  private String createDeliveringNotificationMessage(Order order) {
    List<OrderItem> orderItems = order.getOrderItems();
    String firstItemName = orderItems.get(0).getProduct().getName();

    if (orderItems.size() == 1) {
      return firstItemName + " 상품이 배송 중입니다.";
    }

    return firstItemName + " 외 " + (orderItems.size() - 1) + "개의 상품이 배송 중입니다.";
  }
}