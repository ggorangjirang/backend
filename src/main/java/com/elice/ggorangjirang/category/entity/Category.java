package com.elice.ggorangjirang.category.entity;

import com.elice.ggorangjirang.subcategory.entity.Subcategory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Subcategory> subcategories = new ArrayList<>();

    public void update(String categoryName) {
        this.categoryName = categoryName;
    }
}
