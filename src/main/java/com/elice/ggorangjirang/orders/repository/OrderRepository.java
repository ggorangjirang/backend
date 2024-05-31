package com.elice.ggorangjirang.orders.repository;

import com.elice.ggorangjirang.global.aggregation.sale.SaleAggregationRepository;
import com.elice.ggorangjirang.orders.entity.Order;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>, SaleAggregationRepository {

  // 추후 userId 매핑 후 변경
  List<Order> findAllByUsers_Id(Long userId);

  Optional<Order> findByIdAndUsers_Id(Long orderId, Long userId);

  @Query(value = "SELECT DATE_FORMAT(DATE_SUB(o.order_date, INTERVAL (DAYOFWEEK(o.order_date) - DAYOFWEEK(:start)) DAY), '%Y-%m-%d') AS week, " +
      "SUM(o.total_all_price) AS totalPrice, " +
      "COUNT(o.total_all_price) " +
      "FROM order s " +
      "WHERE o BETWEEN :start AND :end " +
      "AND o.order_status = 'CANCEL' " +
      "GROUP BY week " +
      "ORDER BY week", nativeQuery = true)
  List<Object[]> findRefundsByWeek(@Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end);
}
