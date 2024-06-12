package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.global.exception.constant.ExceptionConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // Domain Common Exception
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionConstants.INTERNAL_SERVER_ERROR_CODE, ExceptionConstants.INTERNAL_SERVER_ERROR_MSG),
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, ExceptionConstants.INVALID_PARAMETER_CODE, ExceptionConstants.INVALID_PARAMETER_MSG),
  ACCESS_DENIED(HttpStatus.FORBIDDEN, ExceptionConstants.ACCESS_DENIED_CODE, ExceptionConstants.ACCESS_DENIED_MSG),

  // Domain Custom Exception
  // SAMPLE
  REFRESH_NOT_VALID(HttpStatus.FORBIDDEN, ExceptionConstants.REFRESH_NOT_VALID_CODE, ExceptionConstants.REFRESH_NOT_VALID_MSG),
  INVALID_PRODUCT_DATA(HttpStatus.BAD_REQUEST, ExceptionConstants.INVALID_PRODUCT_DATA_CODE, ExceptionConstants.INVALID_PRODUCT_DATA_MSG),


  // Product Specific Exception
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionConstants.PRODUCT_NOT_FOUND_CODE, ExceptionConstants.PRODUCT_NOT_FOUND_MSG),
  PRODUCT_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, ExceptionConstants.PRODUCT_OUT_OF_STOCK_CODE, ExceptionConstants.PRODUCT_OUT_OF_STOCK_MSG),

  DATA_INTEGRITY_VIOLATION(HttpStatus.CONFLICT, ExceptionConstants.DATA_INTEGRITY_VIOLATION_CODE, ExceptionConstants.DATA_INTEGRITY_VIOLATION),

  // Order Specific Exception
  ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionConstants.ORDER_NOT_FOUND_CODE, ExceptionConstants.ORDER_NOT_FOUND_MSG),
  ORDER_CANNOT_CANCEL_DELIVERING(HttpStatus.CONFLICT, ExceptionConstants.ORDER_CANNOT_CANCEL_DELIVERING_CODE, ExceptionConstants.ORDER_CANNOT_CANCEL_DELIVERING_MSG),
  ORDER_CANNOT_CANCEL_DELIVERED(HttpStatus.CONFLICT, ExceptionConstants.ORDER_CANNOT_CANCEL_DELIVERED_CODE, ExceptionConstants.ORDER_CANNOT_CANCEL_DELIVERED_MSG),

  // User Specific Exception
  EMAIL_NOT_VERIFIED(HttpStatus.UNAUTHORIZED, ExceptionConstants.EMAIL_NOT_VERIFIED_CODE, ExceptionConstants.EMAIL_NOT_VERIFIED_MSG),
  BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, ExceptionConstants.BAD_CREDENTIALS_CODE, ExceptionConstants.BAD_CREDENTIALS_MSG),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionConstants.USER_NOT_FOUND_CODE, ExceptionConstants.USER_NOT_FOUND_MSG);

  private final HttpStatus status;
  private final String code;
  private final String msg;

  ErrorCode(HttpStatus status, String code, String msg) {
    this.status = status;
    this.code = code;
    this.msg = msg;
  }
}
