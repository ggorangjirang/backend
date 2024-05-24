package com.elice.ggorangjirang.subcategories.entity;

import com.elice.ggorangjirang.categories.entity.Category;
import com.elice.ggorangjirang.products.entity.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "subcategories")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subcategory_name", nullable = false)
    private String subcategoryName;

    @Column(name = "category_id", nullable = true)
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    private List<Product> products = new ArrayList<>();

    public void update(String subcategoryName, Category category, Long categoryId) {
        this.subcategoryName = subcategoryName;
        this.category = category;
        this.categoryId = categoryId;
    }

    @Builder
    public Subcategory(String subcategoryName, Long categoryId) {
        this.subcategoryName = subcategoryName;
        this.categoryId = categoryId;
        this.category = new Category();
        this.category.setId(categoryId);
    }
}
