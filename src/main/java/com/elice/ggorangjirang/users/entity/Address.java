package com.elice.ggorangjirang.users.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    // 주소 정보
    private String zipcode;
    private String streetAddress;
    private String detailAddress;
}
