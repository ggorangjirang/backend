package com.elice.ggorangjirang.global.aggregation.sale.dto;

import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {

  public CreatedSaleResponse toResCreateSale(Tuple tupleSale) {

    return new CreatedSaleResponse(tupleSale.get(0, Long.class), tupleSale.get(1, Long.class));
  }

  // QueryDsl tuple mapper
  public SaleViewResponse toResSale(List<Tuple> sales, List<Tuple> refunds) {
    String[] dateTimeArray = sales.stream()
        .map(tuple -> tuple.get(0, String.class))
        .toArray(String[]::new);
    Long[] totalSalesArray = sales.stream()
        .map(tuple -> tuple.get(1, Long.class))
        .toArray(Long[]::new);
    Long[] totalOrdersArray = sales.stream()
        .map(tuple -> tuple.get(2, Long.class))
        .toArray(Long[]::new);

    Integer[] totalRefundsArray = refunds.stream()
        .map(tuple -> tuple.get(1, Integer.class))
        .toArray(Integer[]::new);

    return new SaleViewResponse(dateTimeArray, totalSalesArray, totalOrdersArray, totalRefundsArray);
  }

  // Native query mapper
  public SaleViewResponse toResSaleFromObjects(List<Object[]> sales, List<Object[]> refunds) {
    String[] dateTimeArray = sales.stream()
        .map(array -> (String) array[0])
        .toArray(String[]::new);
    Long[] totalSalesArray = sales.stream()
        .map(array -> ((Number) array[1]).longValue())
        .toArray(Long[]::new);
    Long[] totalOrdersArray = sales.stream()
        .map(array -> ((Number) array[2]).longValue())
        .toArray(Long[]::new);

    Integer[] totalRefundsArray = refunds.stream()
        .map(array -> ((Number) array[1]).intValue())
        .toArray(Integer[]::new);

    return new SaleViewResponse(dateTimeArray, totalSalesArray, totalOrdersArray, totalRefundsArray);
  }
}
