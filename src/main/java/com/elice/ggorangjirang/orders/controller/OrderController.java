package com.elice.ggorangjirang.orders.controller;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.orders.dto.OrderRequest;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.orders.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;
  private final OrderItemService orderItemService;
  private final DeliveryService deliveryService;

  // 주문 생성
  @PostMapping("")
  public ResponseEntity<Void> saveOrder(@RequestBody OrderRequest request){
    Deliveries deliveries = deliveryService.getDeliveryById(request.getDeliveryId());

    List<OrderItem> orderItems = new ArrayList<>();

    for(Long orderItemId : request.getOrderItemId()){
      orderItems.add(orderItemService.findById(orderItemId));
    }

    Order order = orderService.create(request.getUserId(), deliveries,orderItems);

    return ResponseEntity.ok().build();
  }

}
