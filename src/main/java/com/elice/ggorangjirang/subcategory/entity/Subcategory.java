package com.elice.ggorangjirang.subcategory.entity;

import com.elice.ggorangjirang.category.entity.Category;
import com.elice.ggorangjirang.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

}
