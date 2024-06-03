package com.elice.ggorangjirang.orders.controller;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.orders.dto.OrderRequest;
import com.elice.ggorangjirang.orders.dto.OrderResponse;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.orders.service.OrderService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
  private final JwtService jwtService;

  // 주문 생성
  @PostMapping("")
  public ResponseEntity<Long> addOrder(@RequestBody OrderRequest request){

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
  public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam Long usersId){
    List<OrderResponse> orderResponses = orderService.findAllByUserId(usersId)
        .stream()
        .map(OrderResponse::new)
        .toList();

    return ResponseEntity.ok().body(orderResponses);
  }

  // 하나의 주문 검색
  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrder(@PathVariable(value = "id") Long id){
    Order order = orderService.findById(id);
    return ResponseEntity.ok().body(new OrderResponse(order));
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> deleteOrder(
      @PathVariable Long orderId, HttpServletRequest request) {
    try {

      String token = jwtService.extractAccessToken(request)
          .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

      String email = jwtService.extractEmail(token)
          .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

      // 서비스 메소드 호출하여 주문 삭제
      orderService.deleteByUserIdAndOrderId(email, orderId);
      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException | IllegalStateException e) {
      return ResponseEntity.badRequest().build();
    }
  }


}
