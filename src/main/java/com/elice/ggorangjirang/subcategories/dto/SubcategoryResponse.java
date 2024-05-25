package com.elice.ggorangjirang.subcategories.dto;

import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryResponse {
    private String subcategoryName;
    private String categoryName;
}