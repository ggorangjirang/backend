package com.elice.ggorangjirang.global.aggregation.sale;

import static com.elice.ggorangjirang.global.aggregation.sale.QSale.sale;
import static com.elice.ggorangjirang.orders.entity.QOrder.order;

import com.elice.ggorangjirang.orders.entity.OrderStatus;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SaleAggregationRepositoryImpl implements SaleAggregationRepository {

  private final JPAQueryFactory jpaQueryFactory;

  private static final HashMap<ChronoUnit, DateTemplate<String>> saleExpressionMapper = new HashMap<ChronoUnit, DateTemplate<String>>();
  private static final HashMap<ChronoUnit, DateTemplate<String>> orderExpressionMapper = new HashMap<ChronoUnit, DateTemplate<String>>();

  static {
    saleExpressionMapper.put(
        ChronoUnit.HOURS,
        Expressions.dateTemplate(
            String.class, "DATE_FORMAT({0}, {1})", sale.dateTime, "%Y-%m-%d %H:00"));

    saleExpressionMapper.put(
        ChronoUnit.DAYS,
        Expressions.dateTemplate(
            String.class, "DATE_FORMAT({0}, {1})", sale.dateTime, "%Y-%m-%d"));

    saleExpressionMapper.put(
        ChronoUnit.MONTHS,
        Expressions.dateTemplate(
            String.class, "DATE_FORMAT({0}, '%Y-%m-01')", sale.dateTime));

    orderExpressionMapper.put(
        ChronoUnit.HOURS,
        Expressions.dateTemplate(
            String.class, "DATE_FORMAT({0}, {1})", order.orderDate, "%Y-%m-%d %H:00"));

    orderExpressionMapper.put(
        ChronoUnit.DAYS,
        Expressions.dateTemplate(
            String.class, "DATE_FORMAT({0}, {1})", order.orderDate, "%Y-%m-%d"));

    orderExpressionMapper.put(
        ChronoUnit.MONTHS,
        Expressions.dateTemplate(
            String.class, "DATE_FORMAT({0}, '%Y-%m-01')", order.orderDate));
  }

  @Override
  @Transactional
  public void cancelPendingOrders(LocalDateTime start, LocalDateTime end) {

    jpaQueryFactory.update(order)
        .set(order.orderStatus, OrderStatus.EXPIRED)
        .where(order.orderStatus.eq(OrderStatus.PENDING)
            .and(order.orderDate.between(start, end)))
        .execute();
  }

  @Override
  public Tuple getSaleByOrders(LocalDateTime start, LocalDateTime end) {

    BooleanExpression dateRangeCondition = order.orderDate.between(start, end);
    BooleanExpression conFirmedOrderStatus = order.orderStatus.in(OrderStatus.ORDER, OrderStatus.DELIVERY, OrderStatus.COMPLETE);

    return jpaQueryFactory
        .select(order.totalAllPrice.sum(), order.count())
        .from(order)
        .where(dateRangeCondition.and(conFirmedOrderStatus))
        .fetchOne();
  }

  @Override
  public List<Tuple> findSalesByTimeUnit(
      ChronoUnit chronoUnit, LocalDateTime start, LocalDateTime end) {

    return jpaQueryFactory
        .select(saleExpressionMapper.get(chronoUnit), sale.totalSales.sum(), sale.totalOrders.sum())
        .from(sale)
        .where(sale.dateTime.between(start, end))
        .groupBy(saleExpressionMapper.get(chronoUnit))
        .orderBy(saleExpressionMapper.get(chronoUnit).asc())
        .fetch();
  }

  @Override
  public List<Tuple> findRefundsByTimeUnit(ChronoUnit chronoUnit, LocalDateTime start,
      LocalDateTime end) {

    BooleanExpression dateRangeCondition = order.orderDate.between(start, end);
    BooleanExpression cancelStatusCondition = order.orderStatus.eq(OrderStatus.CANCEL);

    return jpaQueryFactory
        .select(orderExpressionMapper.get(chronoUnit), order.totalAllPrice.sum())
        .from(order)
        .where(dateRangeCondition.and(cancelStatusCondition))
        .groupBy(orderExpressionMapper.get(chronoUnit))
        .orderBy(orderExpressionMapper.get(chronoUnit).asc())
        .fetch();
  }
}
