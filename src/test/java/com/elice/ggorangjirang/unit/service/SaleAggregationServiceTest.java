package com.elice.ggorangjirang.unit.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.elice.ggorangjirang.global.aggregation.sale.SaleAggregationService;
import com.elice.ggorangjirang.global.aggregation.sale.SaleRepository;
import com.elice.ggorangjirang.global.aggregation.sale.dto.SaleMapper;
import com.elice.ggorangjirang.global.aggregation.sale.dto.SaleViewResponse;
import com.elice.ggorangjirang.global.exception.hierachy.common.InvalidParameterException;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaleAggregationServiceTest {

  @Mock private OrderRepository orderRepository;

  @Mock private SaleRepository saleRepository;

  // Mapper를 실제 구현체로 사용하기 위해 @InjectMock을 안하고, 직접 의존성 주입
  private SaleAggregationService saleAggregationService;

  private final SaleMapper saleMapper = new SaleMapper();

  @BeforeEach
  void setUp() {
    saleAggregationService =
        new SaleAggregationService(orderRepository, saleRepository, saleMapper);
  }

  @DisplayName("[실패] 요청 파라미터 timeUnit이 NULL이다.")
  @Test
  void validateParamIsNULL_timeUnit() {

    assertThatThrownBy(() -> saleAggregationService.getSales(null, null, null))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("parameters can't be null");
  }

  @DisplayName("[실패] 요청 파라미터 startTime이 NULL이다.")
  @Test
  void validateParamIsNULL_startTime() {

    assertThatThrownBy(() -> saleAggregationService.getSales("weekly", null, null))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("parameters can't be null");
  }

  @DisplayName("[실패] 요청 파라미터가 endTime이 NULL이다.")
  @Test
  void validateParamIsNULL_endTime() {

    assertThatThrownBy(() -> saleAggregationService.getSales("weekly", null, null))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("parameters can't be null");
  }

  @DisplayName("[실패] 요청 파라미터 timeUnit이 비어있다.")
  @Test
  void validateParamIsEmpty_timeUnit() {

    assertThatThrownBy(
            () -> saleAggregationService.getSales("", LocalDateTime.now(), LocalDateTime.now()))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("timeUnit can't be empty");
  }

  @DisplayName("[실패] 지원되지 않는 Timeunit이다.")
  @Test
  void validateParamIsEmpty_unsupported_timeUnit() {
    String unSupportedTimeUnit = "yearly";

    assertThatThrownBy(
            () ->
                saleAggregationService.getSales(
                    unSupportedTimeUnit, LocalDateTime.now(), LocalDateTime.now()))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("unsupported time unit: " + unSupportedTimeUnit);
  }

  @DisplayName("[실패] 선택한 기간이 보여주는 단위(시간)보다 짧다.")
  @Test
  void validateTimeInterval_hour() {

    assertThatThrownBy(
            () ->
                saleAggregationService.getSales("hourly", LocalDateTime.now(), LocalDateTime.now()))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("the selected time period is short.");
  }

  @DisplayName("[실패] 선택한 기간이 보여주는 단위(일)보다 짧다.")
  @Test
  void validateTimeInterval_day() {

    assertThatThrownBy(
            () ->
                saleAggregationService.getSales(
                    "daily", LocalDateTime.now().minusDays(1).plusHours(1), LocalDateTime.now()))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("the selected time period is short.");
  }

  @DisplayName("[실패] 선택한 기간이 보여주는 단위(월)보다 짧다.")
  @Test
  void validateTimeInterval_month() {

    assertThatThrownBy(
            () ->
                saleAggregationService.getSales(
                    "monthly",
                    LocalDateTime.of(2024, 05, 02, 0, 0),
                    LocalDateTime.of(2024, 6, 01, 0, 0)))
        .isInstanceOf(InvalidParameterException.class)
        .hasMessageContaining("the selected time period is short.");
  }

  @DisplayName("[성공] 사용자가 선택한 날짜에 있는 데이터를 가져온 후 SaleViewResponse로 변환한다.")
  @Test
  void timeUnitHourly_success() {

    LocalDateTime searchStart = LocalDateTime.of(2024, 05, 01, 0, 0);
    LocalDateTime searchEndHour = LocalDateTime.of(2024, 05, 02, 0, 0);

    LocalDateTime dataStart1 = LocalDateTime.of(2024, 05, 01, 1, 0);
    LocalDateTime dataStart2 = LocalDateTime.of(2024, 05, 01, 23, 0);

    Tuple saleTuple1 = mock(Tuple.class);
    Tuple saleTuple2 = mock(Tuple.class);

    when(saleTuple1.get(0, String.class)).thenReturn(dataStart1.toString());
    when(saleTuple1.get(1, Long.class)).thenReturn(1000L);
    when(saleTuple1.get(2, Long.class)).thenReturn(10L);

    when(saleTuple2.get(0, String.class)).thenReturn(dataStart2.toString());
    when(saleTuple2.get(1, Long.class)).thenReturn(1000L);
    when(saleTuple2.get(2, Long.class)).thenReturn(10L);

    Tuple orderTuple1 = mock(Tuple.class);
    Tuple orderTuple2 = mock(Tuple.class);

    when(orderTuple1.get(1, Integer.class)).thenReturn(100);

    when(orderTuple2.get(1, Integer.class)).thenReturn(100);

    List<Tuple> saleDummy = Arrays.asList(saleTuple1, saleTuple2);
    List<Tuple> orderDummy = Arrays.asList(orderTuple1, orderTuple2);

    when(saleRepository.findSalesByTimeUnit(ChronoUnit.HOURS, searchStart, searchEndHour))
        .thenReturn(saleDummy);
    when(orderRepository.findRefundsByTimeUnit(ChronoUnit.HOURS, searchStart, searchEndHour))
        .thenReturn(orderDummy);
    SaleViewResponse sales = saleAggregationService.getSales("hourly", searchStart, searchEndHour);

    String[] expectedDateTime = {"2024-05-01T01:00", "2024-05-01T23:00"};
    Long[] expectedTotalSales = {1000L, 1000L};
    Long[] expectedTotalOrders = {10L, 10L};
    Integer[] expectedTotalRefunds = {100, 100};
    SaleViewResponse expected =
        new SaleViewResponse(
            expectedDateTime, expectedTotalSales, expectedTotalOrders, expectedTotalRefunds);

    assertThat(sales).usingRecursiveComparison().isEqualTo(expected);
  }
}
