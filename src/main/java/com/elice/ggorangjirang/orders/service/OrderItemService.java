package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
  private final OrderItemRepository orderItemRepository;


  // 주문상품 추가
  public OrderItem createOrderItem(Product product, int orderPrice, int quantity){
    return orderItemRepository.save(OrderItem.createOrderItem(product,orderPrice,quantity));
  }

  // 주문상품 조회
  public OrderItem findById(Long id){
    return orderItemRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("not found orderItem Id: "+ id));
  }

  public void deleteById(Long id){
    orderItemRepository.deleteById(id);
  }

}
