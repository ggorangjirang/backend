package com.elice.ggorangjirang.orders.dto;


import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.products.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemSaveRequest {

  private Long productId;

  private Integer price;

  private Integer quantity;


  public OrderItem toEntity(Product product) {
    return OrderItem.builder()
        .product(product)
        .quantity(quantity)
        .price(product.getPrice() * quantity)
        .build();
  }
}
