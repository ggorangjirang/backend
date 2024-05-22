package com.elice.ggorangjirang.orders.controller;


import com.elice.ggorangjirang.orders.dto.OrderSaveRequest;
import com.elice.ggorangjirang.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

  private final OrderService orderService;

  @PostMapping("")
  public ResponseEntity<String> saveOrder(OrderSaveRequest request){
    orderService.saveOrder(request);

    return ResponseEntity.ok("주문 성공");
  }


}
