package com.elice.ggorangjirang.deliveries.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Deliveries {

  @Id
  private Long id;

 // @Column
 // private arrivalDate;

  @Column
  private String status;

  @Column
  private String orderId;

  @Column
  private String zipcode;

  @Column
  private String streetAddress;

  @Column
  private String detailAddress;

}
