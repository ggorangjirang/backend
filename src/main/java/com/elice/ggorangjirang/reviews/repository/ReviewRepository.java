package com.elice.ggorangjirang.reviews.repository;

import com.elice.ggorangjirang.reviews.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 상품 아이디를 기준으로 Review 조회
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    Page<Review> findByProduct(@Param("productId") Long productId, Pageable pageable);

    // 유저 id 값을 기준으로 Review 조회
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    Page<Review> findByUserId(@Param("userId") Long userId, Pageable pageable);

    boolean existsByProduct_IdAndUser_Id(Long productId, Long userId);
}
