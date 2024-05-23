package com.elice.ggorangjirang.orders.entity;


import com.elice.ggorangjirang.products.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;

  private Integer price;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Builder
  public OrderItem(Integer quantity, Integer price, Product product) {
    this.quantity = quantity;
    this.price = price;
    this.product = product;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
