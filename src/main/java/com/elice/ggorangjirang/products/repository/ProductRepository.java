package com.elice.ggorangjirang.products.repository;

import com.elice.ggorangjirang.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.stock <= 20 AND p.discountRate >= 35")
    List<Product> findLimitedSaleProducts();

    @Query("SELECT p FROM Product p WHERE p.stock <= 20 AND p.discountRate >= 35")
    Page<Product> findLimitedSaleProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.viewCount >= 700 AND p.orderCount >= 100")
    List<Product> findBestSellingProducts();

    @Query("SELECT p FROM Product p WHERE p.viewCount >= 700 AND p.orderCount >= 100")
    Page<Product> findBestSellingProducts(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.orderCount DESC")
    Page<Product> findByOrderCountDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.createdAt DESC")
    Page<Product> findByCreatedAtDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    Page<Product> findByPriceAsc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    Page<Product> findByPriceDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.viewCount DESC")
    Page<Product> findByViewCountDesc(Pageable pageable);


}
