package com.elice.ggorangjirang.websocket;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DeliveryStatusController {

  @Autowired
  private DeliveryService deliveryService;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  //배송상태 변했을때 실시간 알람 표시
  @MessageMapping("/updateAlarm")
  public void updateDeliveryStatus(DeliveryStatusDto statusDto) {
    long deliveryId = statusDto.getId();
    String status = statusDto.getStatus();

    Deliveries updatedDelivery = deliveryService.updateDeliveryStatus(deliveryId, status);
    Order order = updatedDelivery.getOrder();

    if (order != null && order.getUsers() != null) {
      Long userId = order.getUsers().getId();
      String notificationMessage = getNotificationMessage(status);

      messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/sub/deliveryStatus", notificationMessage);
    }
  }

  // 실시간 알람 표시 함수
  private String getNotificationMessage(String status) {
    if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
      return "배달이 완료되었습니다.";
    } else if ("DELIVERING".equalsIgnoreCase(status)) {
      return "배달이 시작되었습니다.";
    }
    return "";
  }

  //배송 상태 업데이트 되었을때 위에 종모양 알람 표시
  @MessageMapping("/bellAlarm")
  public void handleDeliveryCompleteNotification(DeliveryStatusDto statusDto) {
    long deliveryId = statusDto.getId();
    String status = statusDto.getStatus();

    Deliveries updatedDelivery = deliveryService.updateDeliveryStatus(deliveryId, status);
    Order order = updatedDelivery.getOrder();


    if (order != null && order.getUsers() != null) {
      Long userId = order.getUsers().getId();
      LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
      updatedDelivery.setArrivalDate(now);

      String notificationMessage = "";

      if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
        notificationMessage = createDeliveryCompleteNotificationMessage(order);
      } else if ("DELIVERING".equalsIgnoreCase(status)) {
        notificationMessage = createDeliveringNotificationMessage(order);
      }


      messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/sub/deliveryStatus", notificationMessage);
    }
  }


  // 종모양 알림 표시 함수
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