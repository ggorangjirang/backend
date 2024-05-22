package com.elice.ggorangjirang.products.entity;

import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products")
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

    @Column(name = "image_url")
    private String imageUrl;

    // 상품 상세페이지 조회수
    @Column(name = "view_count")
    private int viewCount = 0;

    // 판매량을 나타내는 수
    @Column(name = "order_count")
    private int orderCount = 0;

    // 재고량. 주문 결제가 완료되면 판매량만큼 stock 을 깎아줘야 함
    @Column(name = "stock")
    private int stock;

    @Column(name = "is_sold_out", nullable = false)
    private boolean isSoldOut = (stock == 0);

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

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
        this.isSoldOut = (stock == 0);
        this.category = category;
        this.subcategory = subcategory;
        this.isDeleted = false;
    }


    // ProductController 에서 호출하기
    public void addViewCount() {
        this.viewCount += 1;
    }


    // 결제 완료 시 quantity 만큼 판매량 증가
    public void addOrderCount(int quantity) {
        this.orderCount = this.orderCount + quantity;
    }

    // 결제 완료 시 quantity 만큼 재고량 감소 및 재고량 0 도달 시 품절 처리
    public void updateStock(int quantity) {
        this.stock = this.stock - quantity;
        if(this.stock <= 0) {
            this.stock = 0;
            this.isSoldOut = true;
        } else {
            this.isSoldOut = false;
        }
    }
}
