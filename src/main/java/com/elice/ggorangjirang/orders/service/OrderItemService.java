package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.orders.dto.ReviewableOrderItemResponse;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;
  private final ReviewRepository reviewRepository;


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

  public List<ReviewableOrderItemResponse> getReviewableOrderItems(Long userId) {
    List<OrderItem> deliveredOrderItems = orderItemRepository.findByOrder_Users_IdAndOrder_Deliveries_Status(userId, "DELIVERY_COMPLETE");

    return deliveredOrderItems.stream()
            .filter(orderItem -> !reviewRepository.existsByProduct_IdAndUser_Id(orderItem.getProduct().getId(), userId))
            .map(orderItem -> new ReviewableOrderItemResponse(
                    orderItem.getProduct().getId(),
                    userId,
                    orderItem.getProduct().getName(),
                    orderItem.getProduct().getProductImageUrl(),
                    orderItem.getOrderPrice(),
                    orderItem.getQuantity(),
                    orderItem.getTotalPrice()
            ))
            .collect(Collectors.toList());
  }
}
