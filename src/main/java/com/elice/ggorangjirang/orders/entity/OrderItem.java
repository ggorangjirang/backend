package com.elice.ggorangjirang.orders.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;

  private Integer price;

  // 추후 매핑;
  private Long productId;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Builder
  public OrderItem(Integer quantity, Integer price, Long productId) {
    this.quantity = quantity;
    this.price = price;
    this.productId = productId;
  }
}
