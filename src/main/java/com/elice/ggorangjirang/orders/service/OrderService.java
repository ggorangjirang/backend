package com.elice.ggorangjirang.orders.service;

import com.elice.ggorangjirang.orders.dto.OrderSaveRequest;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  // 추후 userId 인스턴스로 추가;
  public Order saveOrder(OrderSaveRequest quest){
    return orderRepository.save(quest.toEntity());
  }

}
