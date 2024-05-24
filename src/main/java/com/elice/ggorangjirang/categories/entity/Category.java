package com.elice.ggorangjirang.categories.entity;

import com.elice.ggorangjirang.subcategories.entity.Subcategory;
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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();

    public void update(String categoryName) {
        this.categoryName = categoryName;
    }

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
