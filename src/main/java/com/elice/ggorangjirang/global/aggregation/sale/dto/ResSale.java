package com.elice.ggorangjirang.global.aggregation.sale.dto;

public record ResSale(
    String[] dateTime,
    Long[] totalSales,
    Long[] totalOrders,
    Integer[] totalRefunds
) {}
