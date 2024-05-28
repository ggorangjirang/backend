package com.elice.ggorangjirang.orders.controller;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.orders.dto.OrderRequest;
import com.elice.ggorangjirang.orders.dto.OrderResponse;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.orders.service.OrderService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
  private final OrderService orderService;
  private final OrderItemService orderItemService;
  private final DeliveryService deliveryService;
  private final UserService userService;

  // 주문 생성
  @PostMapping("")
  public ResponseEntity<Long> saveOrder(@RequestBody OrderRequest request){
    // 추후에 유저 검증 서비스 생기면 변경 예정
    Users users = userService.findById(request.getUserId());
    Deliveries deliveries = deliveryService.getDeliveryById(request.getDeliveryId());

    List<OrderItem> orderItems = new ArrayList<>();

    for(Long orderItemId : request.getOrderItemId()){
      orderItems.add(orderItemService.findById(orderItemId));
    }

    Order order = orderService.create(users, deliveries,orderItems);

    return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
  }

  // 주문 목록 검색
  @GetMapping("")
  public ResponseEntity<List<OrderResponse>> findOrders(@RequestParam Long usersId){
    List<OrderResponse> orderResponses = orderService.findAllByUserId(usersId)
        .stream()
        .map(OrderResponse::new)
        .toList();

    return ResponseEntity.ok().body(orderResponses);
  }

  // 하나의 주문 검색
  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> findOrder(@PathVariable(value = "id") Long id){
    Order order = orderService.findById(id);
    return ResponseEntity.ok().body(new OrderResponse(order));
  }


}
