package com.elice.ggorangjirang.orders.controller;

import com.elice.ggorangjirang.orders.dto.OrderItemRequest;
import com.elice.ggorangjirang.orders.dto.OrderItemsResponse;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderitem")
public class OrderItemController {
  private final ProductService productService;
  private final OrderItemService orderItemService;

  @PostMapping("")
  public ResponseEntity<Long> createOrderItem(@RequestBody OrderItemRequest request) {
    Product product = productService.findProduct(request.getProductId());

    OrderItem createdOrderItem = orderItemService.createOrderItem(product, product.getPrice(), request.getQuantity());

    return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem.getId());
  }

  @PostMapping("/list")
  public ResponseEntity<List<Long>> createOrderItems(@RequestBody List<OrderItemRequest> request){

    List<Long> orderItemIds = new ArrayList<>();

    for(OrderItemRequest orderRequest : request){
      Product product = productService.findProduct(orderRequest.getProductId());
      OrderItem createdOrderItem = orderItemService.createOrderItem(product, product.getPrice(), orderRequest.getQuantity());
      orderItemIds.add(createdOrderItem.getId());
    }
    return  ResponseEntity.status(HttpStatus.CREATED).body(orderItemIds);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderItemsResponse> getOrderItems(@PathVariable(value = "id") long id){
    OrderItem orderItem = orderItemService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(new OrderItemsResponse(orderItem));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrderItem(@PathVariable(value = "id") long id){
    orderItemService.deleteById(id);

    return ResponseEntity.ok().build();
  }


}
