package com.elice.ggorangjirang.websocket.Controller;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.users.service.UserService;
import com.elice.ggorangjirang.websocket.dto.DeliveryUpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DeliveryStatusController {

  @Autowired
  private DeliveryService deliveryService;

  @Autowired
  private UserService userService;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/deliveryUpdate")
  public void updateDeliveryStatus(DeliveryUpdateMessage message) {
    long id = message.getId();
    String status = message.getStatus();
    DeliveryStatus(id, status);
  }

  private void DeliveryStatus(long id, String status) {

    Deliveries updatedDelivery = deliveryService.updateDeliveryStatus(id, status);


    Order order = updatedDelivery.getOrder();

    // 주문이 존재하고 사용자 정보가 있는지 확인 후 사용자 ID 가져오기
    if (order != null && order.getUsers() != null) {
      Long userId = order.getUsers().getId();


      String notificationMessage = "";
      if ("DELIVERY_COMPLETE".equalsIgnoreCase(status)) {
        notificationMessage = "배달이 완료되었습니다.";
      } else if ("DELIVERING".equalsIgnoreCase(status)) {
        notificationMessage = "배달이 시작되었습니다.";
      }


      messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/topic/deliveryStatus", notificationMessage);
    }
  }
}