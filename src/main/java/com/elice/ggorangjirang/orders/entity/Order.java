package com.elice.ggorangjirang.orders.entity;


import com.elice.ggorangjirang.deliveries.entity.Deliveries;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime orderDate;

  private String status;

  private Integer totalAmount;

  private Long userId; // 추후 매핑

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems;

  @OneToOne(cascade = CascadeType.ALL)
  private Deliveries deliveries;

  @Builder
  public Order(Long userId, List<OrderItem> orderItems, Deliveries deliveries){
    this.orderDate = LocalDateTime.now();
    this.status = "PENDING";
    this.userId = userId;
    this.orderItems = orderItems;
    this.deliveries = deliveries;
    this.totalAmount = calculateTotalAmount(orderItems);
  }
  private Integer calculateTotalAmount(List<OrderItem> orderItems) {
    return orderItems.stream().mapToInt(OrderItem::getPrice).sum();
  }

}
