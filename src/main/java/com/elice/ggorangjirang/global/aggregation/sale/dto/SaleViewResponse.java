package com.elice.ggorangjirang.global.aggregation.sale.dto;

public record SaleViewResponse(
    String[] dateTime,
    Long[] totalSales,
    Long[] totalOrders,
    Integer[] totalRefunds
) {}
