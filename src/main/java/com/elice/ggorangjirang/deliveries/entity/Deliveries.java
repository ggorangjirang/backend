package com.elice.ggorangjirang.deliveries.entity;

import com.elice.ggorangjirang.orders.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Deliveries {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate arrivalDate;

  @Column(nullable = false)
  private String status;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="order_id")
  private Order order;

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

//  @Builder
//  public Deliveries(String zipcode, String streetAddress, String detailAddress){
//    this.zipcode = zipcode;
//    this.streetAddress = streetAddress;
//    this.detailAddress = detailAddress;
//  }

  public static Deliveries createDelivery(String zipcode, String streetAddress, String detailAddress, String status, String request,
      LocalDate arrivalDate, Integer phoneNumber, String name) {
    Deliveries deliveries = new Deliveries();
    deliveries.setZipcode(zipcode);
    deliveries.setStreetAddress(streetAddress);
    deliveries.setDetailAddress(detailAddress);
    deliveries.setStatus(status);
    deliveries.setRequest(request);
    deliveries.setArrivalDate(arrivalDate);
    deliveries.setPhoneNumber(phoneNumber);
    deliveries.setName(name);

    return deliveries;
  }
}
