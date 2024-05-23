package com.elice.ggorangjirang.orders.controller;


import com.elice.ggorangjirang.orders.dto.OrderResponse;
import com.elice.ggorangjirang.orders.dto.OrderSaveRequest;
import com.elice.ggorangjirang.orders.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<String> saveOrder(@RequestBody OrderSaveRequest request){
    orderService.saveOrder(request);

    return ResponseEntity.ok("주문 성공");
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathVariable Long userId) {
    List<OrderResponse> orders = orderService.getOrdersByUserId(userId);

    if (orders.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }


}
