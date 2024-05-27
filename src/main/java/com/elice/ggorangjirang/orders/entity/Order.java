package com.elice.ggorangjirang.orders.entity;

import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import com.elice.ggorangjirang.users.entity.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "deliveries_id")
  private Deliveries deliveries;

  private LocalDateTime orderDate;

  @Column(nullable = false, unique = true)
  private String orderNumber;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Users users;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  //양방향
  public void addOrderItem(OrderItem orderItem){
    orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public void setDeliveries(Deliveries deliveries){
    this.deliveries = deliveries;
    if (deliveries != null) {
      deliveries.setOrder(this);
    }
  }

  // 주문 생성
  public static Order createOrder(Users users, Deliveries delivery, List<OrderItem> orderItems){
    Order order = new Order();
    order.setUsers(users);
    order.setOrderNumber(order.generateOrderNumber());
    order.setDeliveries(delivery);

    for(OrderItem orderItem : orderItems){
      order.addOrderItem(orderItem);
    }
    order.setOrderStatus(OrderStatus.ORDER);
    order.setOrderDate(LocalDateTime.now());

    return order;
  }

  private String generateOrderNumber(){
    SecureRandom random = new SecureRandom();
    long currentTimeMillis = System.currentTimeMillis();
    int randomInt = random.nextInt(100000);
    String timeHex = Long.toHexString(currentTimeMillis);
    String randomHex = Integer.toHexString(randomInt);
    return timeHex.substring(timeHex.length() - 4) + randomHex.substring(randomHex.length() - 4);
  }
}
