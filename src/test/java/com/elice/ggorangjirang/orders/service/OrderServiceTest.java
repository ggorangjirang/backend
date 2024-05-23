package com.elice.ggorangjirang.orders.service;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.orders.dto.OrderDeliverySaveRequest;
import com.elice.ggorangjirang.orders.dto.OrderItemSaveRequest;
import com.elice.ggorangjirang.orders.dto.OrderSaveRequest;
import com.elice.ggorangjirang.orders.entity.Order;

import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private OrderService orderService;

  @Test
  @DisplayName("주문 저장 테스트")
  public void testSaveOrderSuccess() {
    // Given
    Product product1 = Product.builder().name("Product 1").price(50).stock(10).build();
    Product product2 = Product.builder().name("Product 2").price(100).stock(20).build();

    OrderItemSaveRequest itemRequest1 = new OrderItemSaveRequest();
    itemRequest1.setProductId(10L);
    itemRequest1.setQuantity(2);
    OrderItemSaveRequest itemRequest2 = new OrderItemSaveRequest();
    itemRequest2.setProductId(20L);
    itemRequest2.setQuantity(3);

    List<OrderItemSaveRequest> orderItemRequests = Arrays.asList(itemRequest1, itemRequest2);
    OrderDeliverySaveRequest deliveryRequest = new OrderDeliverySaveRequest();
    deliveryRequest.setZipcode("04776");
    deliveryRequest.setStreetAddress("서울 성동구 서울숲길 17");
    deliveryRequest.setDetailAddress("테스트아파트 104-1004");

    OrderSaveRequest request = new OrderSaveRequest();
    request.setUserId(1L);
    request.setOrderItem(orderItemRequests);
    request.setDeliveries(deliveryRequest);

    when(productRepository.findById(10L)).thenReturn(Optional.of(product1));
    when(productRepository.findById(20L)).thenReturn(Optional.of(product2));
    when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

    // When
    Order savedOrder = orderService.saveOrder(request);

    // Then
    assertEquals(1L, savedOrder.getUserId());
    assertEquals(2, savedOrder.getOrderItems().size());
    assertEquals("04776", savedOrder.getDeliveries().getZipcode());
    verify(orderRepository, times(1)).save(any(Order.class));

    // OrderItems에 대한 확인
    for (OrderItem item : savedOrder.getOrderItems()) {
      assertNotNull(item.getProduct());
      assertNotNull(item.getQuantity());
      // 필요에 따라 추가적인 검증을 수행할 수 있습니다.
    }

    // Deliveries에 대한 확인
    assertNotNull(savedOrder.getDeliveries());
    assertNotNull(savedOrder.getDeliveries().getZipcode());
    assertNotNull(savedOrder.getDeliveries().getStreetAddress());
    assertNotNull(savedOrder.getDeliveries().getDetailAddress());
  }

  @Test
  public void testSaveOrderProductNotFound() {
    // Given
    OrderItemSaveRequest itemRequest = new OrderItemSaveRequest();
    itemRequest.setProductId(10L);
    itemRequest.setQuantity(2);

    List<OrderItemSaveRequest> orderItemRequests = Arrays.asList(itemRequest);
    OrderDeliverySaveRequest deliveryRequest = new OrderDeliverySaveRequest();
    deliveryRequest.setZipcode("04776");
    deliveryRequest.setStreetAddress("서울 성동구 서울숲길 17");
    deliveryRequest.setDetailAddress("테스트아파트 104-1004");

    OrderSaveRequest request = new OrderSaveRequest();
    request.setUserId(1L);
    request.setOrderItem(orderItemRequests);
    request.setDeliveries(deliveryRequest);

    when(productRepository.findById(10L)).thenReturn(Optional.empty());

    // When & Then
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      orderService.saveOrder(request);
    });

    assertEquals("Invalid product ID: 10", exception.getMessage());
    verify(orderRepository, never()).save(any(Order.class));
  }
}
