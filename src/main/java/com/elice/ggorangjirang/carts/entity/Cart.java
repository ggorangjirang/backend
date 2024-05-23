package com.elice.ggorangjirang.carts.entity;

import com.elice.ggorangjirang.products.entity.Product;
//import com.elice.ggorangjirang.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private Users user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "cart_count", nullable = false)
    private int cartCount;
}
