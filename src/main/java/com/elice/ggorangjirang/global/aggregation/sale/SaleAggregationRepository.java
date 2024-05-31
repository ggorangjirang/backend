package com.elice.ggorangjirang.global.aggregation.sale;

import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface SaleAggregationRepository {

  void cancelPendingOrders(LocalDateTime start, LocalDateTime end);

  Tuple getSaleByOrders(LocalDateTime start, LocalDateTime end);

  List<Tuple> findSalesByTimeUnit(ChronoUnit chronoUnit, LocalDateTime start, LocalDateTime end);

  List<Tuple> findRefundsByTimeUnit(ChronoUnit chronoUnit, LocalDateTime start, LocalDateTime end);
}
