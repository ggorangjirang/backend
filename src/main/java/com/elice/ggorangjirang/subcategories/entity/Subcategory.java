package com.elice.ggorangjirang.subcategories.entity;

import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.products.entity.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "subcategories")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subcategory_name", nullable = false)
    private String subcategoryName;

    @ManyToOne
    @JoinColumn(name = "parent_category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.REMOVE)
    private List<Product> products = new ArrayList<>();

    public void update(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    @Builder
    public Subcategory(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
