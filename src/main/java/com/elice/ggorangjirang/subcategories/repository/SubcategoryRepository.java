package com.elice.ggorangjirang.subcategories.repository;

import com.elice.ggorangjirang.subcategories.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
