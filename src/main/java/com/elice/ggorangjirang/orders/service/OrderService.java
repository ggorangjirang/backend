package com.elice.ggorangjirang.orders.service;

import com.elice.ggorangjirang.orders.dto.OrderSaveRequest;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  // 추후 userId 인스턴스로 추가;
  @Transactional
  public Order saveOrder(OrderSaveRequest request) {

    List<OrderItem> orderItems = request.getOrderItem().stream()
        .map(itemRequest -> {
          Product product = productRepository.findById(itemRequest.getProductId())
              .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + itemRequest.getProductId()));
          return itemRequest.toEntity(product);
        })
        .collect(Collectors.toList());


    Order order = request.toEntity(orderItems);
    return orderRepository.save(order);
  }


}
