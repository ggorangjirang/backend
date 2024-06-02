package com.elice.ggorangjirang.global.aggregation.sale;

import com.elice.ggorangjirang.global.aggregation.sale.dto.SaleViewResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class SaleAggregationController {

  private final SaleAggregationService saleAggregationService;

  @GetMapping("/api/v1/sales")
  @ResponseBody
  public ResponseEntity<SaleViewResponse> getSales(@RequestParam String timeUnit, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime start, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime end) {
    SaleViewResponse sales = saleAggregationService.getSales(timeUnit, start, end);

    return new ResponseEntity<>(sales, HttpStatus.OK);
  }

  @GetMapping("/admin/aggregation/sales")
  public String showAggregationSales() {

    return "admin-aggregation/sales";
  }
}
