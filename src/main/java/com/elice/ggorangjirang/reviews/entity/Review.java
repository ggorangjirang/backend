package com.elice.ggorangjirang.reviews.entity;

import com.elice.ggorangjirang.products.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Builder
    public Review(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
