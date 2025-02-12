package com.elice.ggorangjirang.orders.service;


import com.elice.ggorangjirang.orders.dto.ReviewableOrderItemResponse;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderItemRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import com.elice.ggorangjirang.reviews.repository.ReviewRepository;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;
  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;


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

  public Page<ReviewableOrderItemResponse> getReviewableOrderItems(String email, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<OrderItem> deliveredOrderItems = orderItemRepository.findByOrder_Users_EmailAndOrder_Deliveries_Status(email, "DELIVERY_COMPLETE", pageable);
    Users user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

    Long userId = user.getId();

    List<ReviewableOrderItemResponse> reviewableItems = deliveredOrderItems.getContent().stream()
            .filter(orderItem -> !reviewRepository.existsByProduct_IdAndUser_Email(orderItem.getProduct().getId(), email))
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

    return new PageImpl<>(reviewableItems, pageable, deliveredOrderItems.getTotalElements());
  }
}
