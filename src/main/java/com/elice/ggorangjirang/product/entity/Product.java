package com.elice.ggorangjirang.product.entity;

import com.elice.ggorangjirang.category.entity.Category;
import com.elice.ggorangjirang.subcategory.entity.Subcategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "discount_rate")
    private float discountRate;

    @Column(name = "stock")
    private int stock;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "view_count")
    private int viewCount = 0;

    @Column(name = "order_count")
    private int orderCount = 0;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    @Builder
    public Product(String name, String description, int price, LocalDateTime expirationDate, float discountRate,
                   int stock, String imageUrl, Category category, Subcategory subcategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.expirationDate = expirationDate;
        this.discountRate = discountRate;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.viewCount = 0;
        this.orderCount = 0;
        this.category = category;
        this.subcategory = subcategory;
    }
}
