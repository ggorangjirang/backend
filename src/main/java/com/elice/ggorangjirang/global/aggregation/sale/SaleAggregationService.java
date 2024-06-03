package com.elice.ggorangjirang.global.aggregation.sale;

import com.elice.ggorangjirang.global.aggregation.sale.dto.ResSale;
import com.elice.ggorangjirang.global.aggregation.sale.dto.SaleMapper;
import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.common.InvalidParameterException;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleAggregationService {

  private static final HashMap<String, ChronoUnit> timeMapper = new HashMap<String, ChronoUnit>();

  private final OrderRepository orderRepository;
  private final SaleRepository saleRepository;
  private final SaleMapper saleMapper;

  static {
    timeMapper.put("hourly", ChronoUnit.HOURS);
    timeMapper.put("daily", ChronoUnit.DAYS);
    timeMapper.put("weekly", ChronoUnit.WEEKS);
    timeMapper.put("monthly", ChronoUnit.MONTHS);
  }

  // 주간 집계 쿼리를 Querydsl 변환하지 못해서 NativeQuery 사용(주간 집계일 때 분기문 처리)
  public ResSale getSales(String timeUnit, LocalDateTime start, LocalDateTime end){

    ChronoUnit chronoUnit = validateTimeIntervals(timeUnit, start, end);

    if (chronoUnit == ChronoUnit.WEEKS){
      List<Object[]> sales = saleRepository.findSalesByWeek(start, end);
      List<Object[]> refunds = orderRepository.findRefundsByWeek(start, end);

      return saleMapper.toResSaleFromObjects(sales, refunds);
    }

    List<Tuple> sales = orderRepository.findSalesByTimeUnit(chronoUnit, start, end);
    List<Tuple> refunds = saleRepository.findRefundsByTimeUnit(chronoUnit, start, end);

    return saleMapper.toResSale(sales, refunds);
  }

  private ChronoUnit validateTimeIntervals(String timeUnit, LocalDateTime start, LocalDateTime end) {

    if (Objects.isNull(timeUnit) || Objects.isNull(start) || Objects.isNull(end)){

      throw new InvalidParameterException(ErrorCode.INVALID_PARAMETER);
    }

    ChronoUnit chronoUnit = timeMapper.get(timeUnit);
    if (chronoUnit == null){

      throw new InvalidParameterException(ErrorCode.INVALID_PARAMETER, "unsupported time unit: " + timeUnit);
    }

    long timeIntervalCount = calculateTimeInterval(chronoUnit, start, end);

    if (timeIntervalCount <= 0){

      throw new InvalidParameterException(ErrorCode.INVALID_PARAMETER, "the selected time period is short.");
    }
    return chronoUnit;
  }

  private long calculateTimeInterval(ChronoUnit chronoUnit, LocalDateTime start, LocalDateTime end) {

    return chronoUnit.between(start, end);
  }
}
