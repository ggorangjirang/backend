package com.elice.ggorangjirang.global.exception.constant;

public class Constant {

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

  private Constant() {}
}