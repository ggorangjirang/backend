package com.elice.ggorangjirang.review.repository;

import com.elice.ggorangjirang.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 유저 아이디를 기준으로 모든 Review 를 조회하는 메서드 추가
}
