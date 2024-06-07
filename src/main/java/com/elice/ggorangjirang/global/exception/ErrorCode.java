package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.global.exception.constant.Constant;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // Domain Common Exception
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, Constant.INTERNAL_SERVER_ERROR_CODE, Constant.INTERNAL_SERVER_ERROR_MSG),
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, Constant.INVALID_PARAMETER_CODE, Constant.INVALID_PARAMETER_MSG),
  ACCESS_DENIED(HttpStatus.FORBIDDEN, Constant.ACCESS_DENIED_CODE, Constant.ACCESS_DENIED_MSG),

  // Domain Custom Exception
  // SAMPLE
  REFRESH_NOT_VALID(HttpStatus.FORBIDDEN, Constant.REFRESH_NOT_VALID_CODE, Constant.REFRESH_NOT_VALID_MSG),
  INVALID_PRODUCT_DATA(HttpStatus.BAD_REQUEST, Constant.INVALID_PRODUCT_DATA_CODE, Constant.INVALID_PRODUCT_DATA_MSG),


  // Product Specific Exception
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, Constant.PRODUCT_NOT_FOUND_CODE, Constant.PRODUCT_NOT_FOUND_MSG),
  PRODUCT_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, Constant.PRODUCT_OUT_OF_STOCK_CODE, Constant.PRODUCT_OUT_OF_STOCK_MSG);


  private final HttpStatus status;
  private final String code;
  private final String msg;

  ErrorCode(HttpStatus status, String code, String msg) {
    this.status = status;
    this.code = code;
    this.msg = msg;
  }
}
