package com.elice.ggorangjirang.deliveries.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Deliveries {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate arrivalDate;

  @Column(nullable = false)
  private String status;

//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name="order_id")
//  private order order;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int phoneNumber;

  @Column(nullable = false)
  private String request;

  @Column(nullable = false)
  private String zipcode;

  @Column(nullable = false)
  private String streetAddress;

  @Column(nullable = false)
  private String detailAddress;

}
