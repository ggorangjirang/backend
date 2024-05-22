package com.elice.ggorangjirang.subcategory.repository;

import com.elice.ggorangjirang.subcategory.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
