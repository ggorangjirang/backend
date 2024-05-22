package com.elice.ggorangjirang.orders.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

  private Integer total_amount;

  private Long userId; // 추후 매핑

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems;

  // 추후 Delivery 매핑
  private String zipcode;
  private String streetAddress;
  private String detailAddress;

  @Builder
  public Order(Long userId, List<OrderItem> orderItems, String zipcode, String streetAddress, String detailAddress){
    this.userId = userId;
    this.orderItems = orderItems;
    this.zipcode = zipcode;
    this.streetAddress = streetAddress;
    this.detailAddress = detailAddress;
  }


}
