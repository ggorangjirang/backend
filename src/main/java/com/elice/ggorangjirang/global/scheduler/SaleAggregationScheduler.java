package com.elice.ggorangjirang.global.scheduler;

import com.elice.ggorangjirang.global.aggregation.sale.Sale;
import com.elice.ggorangjirang.global.aggregation.sale.SaleRepository;
import com.elice.ggorangjirang.global.aggregation.sale.dto.CreatedSaleResponse;
import com.elice.ggorangjirang.global.aggregation.sale.dto.SaleMapper;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleAggregationScheduler {

  private final OrderRepository orderRepository;
  private final SaleMapper saleStatisticMapper;
  private final SaleRepository saleRepository;

  // 매 정시에 (하루 + 1시간) ~ (하루) 전 주문을 찾아 결제 대기 중(PENDING) 상태를 EXPIRED로 변경한다.
  @Scheduled(cron = "0 0 * * * *")
  public void confirmPendingOrder() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime start = now.minusDays(1).minusHours(1);
    LocalDateTime end = now.minusDays(1);

    orderRepository.cancelPendingOrders(start, end);
  }

  // 매 정시에 (하루 + 1시간) ~ (하루) 전 주문을 찾아 결제가 완료되었다면 매출로 잡는다.
  @Scheduled(cron = "0 0 * * * *")
  public void calculateHourlySale() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime start = now.minusDays(1).minusHours(1);
    LocalDateTime end = now.minusDays(1);

    Tuple tupleSale = orderRepository.findSaleByOrders(start, end);

    CreatedSaleResponse CreatedSaleResponse = saleStatisticMapper.toResCreateSale(tupleSale);

    Sale sale = new Sale(now, CreatedSaleResponse.totalSales(), CreatedSaleResponse.totalOrders());
    saleRepository.save(sale);
  }
}
