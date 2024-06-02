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

  @Scheduled(cron = "0 0 * * * *")
  public void confirmPendingOrder() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime start = now.minusDays(1).minusHours(1);
    LocalDateTime end = now.minusDays(1);

    orderRepository.cancelPendingOrders(start, end);
  }

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
