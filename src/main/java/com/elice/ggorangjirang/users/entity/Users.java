package com.elice.ggorangjirang.users.entity;

import com.elice.ggorangjirang.carts.entity.Cart;
import com.elice.ggorangjirang.reviews.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private Address address;

    @Embedded
    private VerificationToken verificationToken;

    @Column(name = "enabled")
    private boolean enabled; // 이메일 인증 여부

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt; // 탈퇴 여부

    @Enumerated(EnumType.STRING)
    private Role role;

    private String socialId;

    private String refreshToken;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    public Users(String name, String email, String password, boolean enalbed){
        this.name = name;
        this.password = password;
        this.email = email;
        this.enabled = enalbed;
        this.createdAt = LocalDateTime.now();
    }

    // 유저 권한 설정 메소드
    public void authorizeUsers() {
        this.role = Role.USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updatedRefreshToken) {
        this.refreshToken = updatedRefreshToken;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    // 탈퇴 여부를 확인하는 메소드
    public boolean isDeleted() {
        return deletedAt != null;
    }

    // 유저 탈퇴 메소드
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}

