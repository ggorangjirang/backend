package com.elice.ggorangjirang.global.exception.constant;

public class ExceptionConstants {

  public static final String INTERNAL_SERVER_ERROR_CODE = "E1";
  public static final String INTERNAL_SERVER_ERROR_MSG = "Internal server error occurred.";

  public static final String INVALID_PARAMETER_CODE = "E2";
  public static final String INVALID_PARAMETER_MSG = "Invalid parameter.";

  public static final String ACCESS_DENIED_CODE = "E3";
  public static final String ACCESS_DENIED_MSG = "Access denied occurred.";

  public static final String REFRESH_NOT_VALID_CODE = "RE1";
  public static final String REFRESH_NOT_VALID_MSG = "The refresh token is invalid. redirect to /login";

  public static final String INVALID_PRODUCT_DATA_CODE = "PROD1";
  public static final String INVALID_PRODUCT_DATA_MSG = "가격 또는 재고가 음수로 입력되었습니다.";

  // Product Specific
  public static final String PRODUCT_NOT_FOUND_CODE = "EP1";
  public static final String PRODUCT_NOT_FOUND_MSG = "Product not found";
  public static final String PRODUCT_OUT_OF_STOCK_CODE = "EP2";
  public static final String PRODUCT_OUT_OF_STOCK_MSG = "Product out of stock";

  public static final String DATA_INTEGRITY_VIOLATION_CODE = "D1";
  public static final String DATA_INTEGRITY_VIOLATION = "Data Integrity Violation";

  // Order Specific
  public static final String ORDER_NOT_FOUND_CODE = "EO1";
  public static final String ORDER_NOT_FOUND_MSG = "Order not found.";
  public static final String ORDER_CANNOT_CANCEL_DELIVERING_CODE = "EO2";
  public static final String ORDER_CANNOT_CANCEL_DELIVERING_MSG = "배송 중이라 취소가 불가능합니다.";
  public static final String ORDER_CANNOT_CANCEL_DELIVERED_CODE = "EO3";
  public static final String ORDER_CANNOT_CANCEL_DELIVERED_MSG = "배송 완료라 취소가 불가능합니다.";
  private ExceptionConstants() {}
}
