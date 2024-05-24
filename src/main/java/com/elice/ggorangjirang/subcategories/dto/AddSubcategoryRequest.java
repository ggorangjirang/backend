package com.elice.ggorangjirang.subcategories.dto;

import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddSubcategoryRequest {

    private String subcategoryName;
    private Long categoryId;

    public Subcategory toEntity() {
        return Subcategory.builder()
                .subcategoryName(subcategoryName)
                .categoryId(categoryId)
                .build();
    }
}
