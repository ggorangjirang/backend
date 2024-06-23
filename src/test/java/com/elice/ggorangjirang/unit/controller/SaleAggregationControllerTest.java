package com.elice.ggorangjirang.unit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elice.ggorangjirang.discord.DiscordWebhook;
import com.elice.ggorangjirang.global.aggregation.sale.SaleAggregationController;
import com.elice.ggorangjirang.global.aggregation.sale.SaleAggregationService;
import com.elice.ggorangjirang.global.aggregation.sale.SaleRepository;
import com.elice.ggorangjirang.global.aggregation.sale.dto.SaleViewResponse;
import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;
import com.elice.ggorangjirang.orders.repository.OrderRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SaleAggregationController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class SaleAggregationControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private SaleAggregationService saleAggregationService;

  @MockBean
  private DiscordWebhook discordWebhook;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private SaleRepository saleRepository;

  LocalDateTime searchStart = LocalDateTime.of(2024, 05, 01, 0, 0);
  LocalDateTime searchEndHour = LocalDateTime.of(2024, 05, 02, 0, 0);

  @BeforeEach
  public void setUp() {

  }

  @DisplayName("[실패] Service 계층에서 InvalidParameterException을 던진다.")
  @Test
  @WithMockUser
  public void validateParamIsEmpty() throws Exception {

    String invalidSearchTimeUnit = "";

    when(saleAggregationService.getSales(invalidSearchTimeUnit, searchStart, searchEndHour))
        .thenThrow(
            new CustomBusinessException(ErrorCode.INVALID_PARAMETER));

    ResultActions response = mockMvc.perform(
      get("/api/v1/sales")
        .contentType(MediaType.APPLICATION_JSON)
        .param("timeUnit", invalidSearchTimeUnit)
        .param("start", searchStart.toString())
        .param("end", searchEndHour.toString()));

    response
        .andExpect(status().isBadRequest())
        .andExpect(content().json("{\"code\":\"E2\",\"msg\":\"Invalid parameter.\"}"));
  }

  @DisplayName("[성공] 유효한 요청일 때 적절한 데이터를 응답한다.")
  @Test
  @WithMockUser
  public void validRequest_success() throws Exception {

    String searchTimeUnit = "hourly";

    String[] expectedDateTime = {"2024-05-01T01:00", "2024-05-01T23:00"};
    Long[] expectedTotalSales = {1000L, 1000L};
    Long[] expectedTotalOrders = {10L, 10L};
    Integer[] expectedTotalRefunds = {100, 100};
    SaleViewResponse expected = new SaleViewResponse(expectedDateTime, expectedTotalSales,
      expectedTotalOrders, expectedTotalRefunds);

    Mockito.when(saleAggregationService.getSales(searchTimeUnit, searchStart, searchEndHour))
        .thenReturn(expected);


    ResultActions response = mockMvc.perform(
      get("/api/v1/sales")
        .contentType(MediaType.APPLICATION_JSON)
        .param("timeUnit", searchTimeUnit)
        .param("start", searchStart.toString())
        .param("end", searchEndHour.toString()));

    response.andExpect(status().isOk())
      .andExpect(jsonPath("$.dateTime[0]").value(expected.dateTime()[0]))
      .andExpect(jsonPath("$.dateTime[1]").value(expected.dateTime()[1]))
      .andExpect(jsonPath("$.totalSales[0]").value(expected.totalSales()[0]))
      .andExpect(jsonPath("$.totalSales[1]").value(expected.totalSales()[1]))
      .andExpect(jsonPath("$.totalOrders[0]").value(expected.totalOrders()[0]))
      .andExpect(jsonPath("$.totalOrders[1]").value(expected.totalOrders()[1]))
      .andExpect(jsonPath("$.totalRefunds[0]").value(expected.totalRefunds()[0]))
      .andExpect(jsonPath("$.totalRefunds[1]").value(expected.totalRefunds()[1]));
  }
}
