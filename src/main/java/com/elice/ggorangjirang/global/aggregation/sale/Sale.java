package com.elice.ggorangjirang.global.aggregation.sale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales")
@NoArgsConstructor
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "date_time", nullable = false)
  private LocalDateTime dateTime;

  @Column(name = "total_sales", nullable = false)
  private Long totalSales;

  @Column(name = "total_orders", nullable = false)
  private Long totalOrders;

  public Sale(LocalDateTime dateTime, Long totalSales, Long totalOrders) {
    this.dateTime = dateTime;
    this.totalSales = totalSales;
    this.totalOrders = totalOrders;
  }
}
