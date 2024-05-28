package com.elice.ggorangjirang.products.entity;

import com.elice.ggorangjirang.carts.entity.CartItem;
import com.elice.ggorangjirang.orders.entity.OrderItem;
import com.elice.ggorangjirang.reviews.entity.Review;
import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
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
    private LocalDate expirationDate;

    @Column(name = "discount_rate")
    private float discountRate;

    @Column(name = "image_url")
    private String productImageUrl;

    @Column(name = "description_image_url")
    private String descriptionImageUrl;

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

    @Column(name = "pseudo_category_id", nullable = true)
    private Long subcategoryId;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems = new ArrayList<>();

    @Builder
    public Product(String name, String description, int price, LocalDate expirationDate, float discountRate,
                   String productImageUrl, String descriptionImageUrl, int stock, Long subcategoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.expirationDate = expirationDate;
        this.discountRate = discountRate;
        this.productImageUrl = productImageUrl;
        this.descriptionImageUrl = descriptionImageUrl;
        this.stock = stock;
        this.subcategoryId = subcategoryId;
        this.subcategory = new Subcategory();
        this.subcategory.setId(subcategoryId);
        this.viewCount = 0;
        this.orderCount = 0;
        this.isSoldOut = (stock == 0);
        this.isDeleted = false;
    }

    public void update(String name, String description, int price, LocalDate expirationDate, float discountRate,
                       String productImageUrl, String descriptionImageUrl, int stock, Long subcategoryId, Subcategory subcategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.expirationDate = expirationDate;
        this.discountRate = discountRate;
        this.productImageUrl = productImageUrl;
        this.descriptionImageUrl = descriptionImageUrl;
        this.stock = stock;
        this.subcategoryId = subcategoryId;
        this.subcategory = subcategory;
    }

    // 상세 페이지 접속 시 조회수 1 증가
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
