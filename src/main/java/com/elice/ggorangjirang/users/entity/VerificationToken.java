package com.elice.ggorangjirang.users.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
}
