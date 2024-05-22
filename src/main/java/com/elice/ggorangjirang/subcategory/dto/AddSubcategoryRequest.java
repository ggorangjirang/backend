package com.elice.ggorangjirang.subcategory.dto;

import com.elice.ggorangjirang.subcategory.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddSubcategoryRequest {
    private String subcategoryName;

    public Subcategory toEntity() {
        return Subcategory.builder()
                .subcategoryName(subcategoryName)
                .build();
    }
}
