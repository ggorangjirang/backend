package com.elice.ggorangjirang.orders.controller;

import com.elice.ggorangjirang.orders.dto.OrderItemRequest;
import com.elice.ggorangjirang.orders.dto.OrderItemsResponse;
import com.elice.ggorangjirang.orders.dto.ReviewableOrderItemResponse;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orderitem")
public class OrderItemController {
    private final ProductService productService;
    private final OrderItemService orderItemService;

//    @PostMapping("/single")
//    public ResponseEntity<Long> createOrderItem(@RequestBody OrderItemRequest request) {
//      Product product = productService.findProduct(request.getProductId());
//
//      OrderItem createdOrderItem = orderItemService.createOrderItem(product, product.getPrice(), request.getQuantity());
//
//      return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem.getId());
//    }

    @PostMapping("")
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

//    @GetMapping("/reviewable")
//    public ResponseEntity<List<ReviewableOrderItemResponse>> getReviewableOrderItems() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Long userId = userDetails.getId();

//        List<ReviewableOrderItemResponse> response = orderItemService.getReviewableOrderItems(userId);
//        return ResponseEntity.ok(response);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable(value = "id") long id){
        orderItemService.deleteById(id);

      return ResponseEntity.ok().build();
    }

}
