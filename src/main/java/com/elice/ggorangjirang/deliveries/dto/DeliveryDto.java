package com.elice.ggorangjirang.deliveries.dto;

import jakarta.persistence.Column;
import java.time.LocalDate;
import lombok.Data;

@Data
public class DeliveryDto {

  private String name;

  private int phoneNumber;

  private String zipcode;

  private String StreetAddress;

  private String detailAddress;

  private String request;

}
