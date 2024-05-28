package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;


  // 주문상품 추가
  public OrderItem createOrderItem(Product product, int orderPrice, int quantity){
    OrderItem orderItem = orderItemRepository.save(OrderItem.createOrderItem(product,orderPrice,quantity));
    productRepository.save(product);
    return orderItemRepository.save(orderItem);
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
