package com.elice.ggorangjirang.subcategories.dto;

import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateSubcategoryRequest {

    private String subcategoryName;
    private Long categoryId;

    public Subcategory toEntity() {
        return Subcategory.builder()
                .subcategoryName(subcategoryName)
                .categoryId(categoryId)
                .build();
    }
}
