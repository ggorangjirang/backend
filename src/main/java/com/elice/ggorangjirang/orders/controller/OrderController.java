package com.elice.ggorangjirang.orders.controller;


import static com.elice.ggorangjirang.global.constant.GlobalConstants.DELIVERY_COMPLETE_EMAIL_CONTENT;
import static com.elice.ggorangjirang.global.constant.GlobalConstants.DELIVERY_COMPLETE_EMAIL_TITLE;
import static com.elice.ggorangjirang.global.constant.GlobalConstants.ORDER_COMPLETE_EMAIL_CONTENT;
import static com.elice.ggorangjirang.global.constant.GlobalConstants.ORDER_COMPLETE_EMAIL_TITLE;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.deliveries.service.DeliveryService;
import com.elice.ggorangjirang.global.email.service.EmailService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.jwt.util.CustomUserDetails;
import com.elice.ggorangjirang.orders.dto.OrderItemDTO;
import com.elice.ggorangjirang.orders.dto.OrderItemRequest;
import com.elice.ggorangjirang.orders.dto.OrderRequest;
import com.elice.ggorangjirang.orders.dto.OrderResponse;
import com.elice.ggorangjirang.orders.entity.Order;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.orders.service.OrderItemService;
import com.elice.ggorangjirang.orders.service.OrderService;
import com.elice.ggorangjirang.products.entity.Product;
import com.elice.ggorangjirang.products.service.ProductService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
  private final OrderService orderService;
  private final OrderItemService orderItemService;
  private final DeliveryService deliveryService;
  private final UserService userService;
  private final JwtService jwtService;
  private final ProductService productService;
  private final EmailService emailService;
  // 주문 생성
  @PostMapping("")
  public ResponseEntity<Long> addOrder(@RequestBody OrderRequest request){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("Authentication object: {}", authentication);

    if (authentication == null || !authentication.isAuthenticated()) {
      log.warn("Authentication is null or not authenticated");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Object principal = authentication.getPrincipal();
    String email = null;

    if (principal instanceof UserDetails) {
      email = ((UserDetails) principal).getUsername();
    } else if (principal instanceof String) {
      email = (String) principal;
    }

    if (email == null || email.equals("anonymousUser")) {
      log.warn("Email is null or anonymousUser");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    log.info("Authenticated user email: {}", email);

    Users users = userService.getUsersInfoByEmail(email);

    // Create delivery using the provided delivery info
    Long deliveryId = deliveryService.addDelivery(request.getDelivery());
    Deliveries deliveries = deliveryService.getDeliveryById(deliveryId);

    List<OrderItem> orderItems = new ArrayList<>();

    for (OrderItemDTO orderItemRequest : request.getOrderItems()) {
      Product product = productService.findProduct(orderItemRequest.getProductId());

      int orderPrice = product.getPrice();
      OrderItem orderItem = orderItemService.createOrderItem(product, orderPrice, orderItemRequest.getQuantity());
      orderItems.add(orderItem);
    }

    Order order = orderService.create(users, deliveries, orderItems);

    String orderNumber = order.getOrderNumber();
    String userEmail = users.getEmail();
    String title = ORDER_COMPLETE_EMAIL_TITLE;
    String content = ORDER_COMPLETE_EMAIL_CONTENT + orderNumber;
    emailService.sendSimpleMessage(userEmail, title, content);

    return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
  }


  // 주문 목록 검색
  @GetMapping("")
  public ResponseEntity<Page<OrderResponse>> getOrders(@RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "5") int size){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("Authentication object: {}", authentication);

    if (authentication == null || !authentication.isAuthenticated()) {
      log.warn("Authentication is null or not authenticated");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Object principal = authentication.getPrincipal();
    String email = null;

    if (principal instanceof UserDetails) {
      email = ((UserDetails) principal).getUsername();
    } else if (principal instanceof String) {
      email = (String) principal;
    }

    if (email == null || email.equals("anonymousUser")) {
      log.warn("Email is null or anonymousUser");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    log.info("Authenticated user email: {}", email);

    Users users = userService.getUsersInfoByEmail(email);
    Long usersId = users.getId();

    Pageable pageable = PageRequest.of(page, size);
    Page<Order> orderPage = orderService.findAllByUserId(usersId, pageable);
    Page<OrderResponse> orderResponsePage = orderPage.map(OrderResponse::new);

    return ResponseEntity.ok().body(orderResponsePage);
  }

  // 하나의 주문 검색
  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrder(@PathVariable(value = "id") Long id){
    Order order = orderService.findById(id);
    return ResponseEntity.ok().body(new OrderResponse(order));
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long orderId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("Authentication object: {}", authentication);

    if (authentication == null || !authentication.isAuthenticated()) {
      log.warn("Authentication is null or not authenticated");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Object principal = authentication.getPrincipal();
    String email = null;

    if (principal instanceof UserDetails) {
      email = ((UserDetails) principal).getUsername();
    } else if (principal instanceof String) {
      email = (String) principal;
    }

    if (email == null || email.equals("anonymousUser")) {
      log.warn("Email is null or anonymousUser");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    log.info("Authenticated user email: {}", email);

    try {
      Users user = userService.getUsersInfoByEmail(email);
      Long userId = user.getId();

      // 서비스 메소드 호출하여 주문 취소
      Order canceledOrder = orderService.cancelOrder(userId, orderId);
      return ResponseEntity.ok().body(new OrderResponse(canceledOrder));
    } catch (IllegalArgumentException | IllegalStateException e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
