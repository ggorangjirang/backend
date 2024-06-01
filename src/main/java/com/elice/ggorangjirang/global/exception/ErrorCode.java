package com.elice.ggorangjirang.global.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // Domain Common Exception
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E1", "Internal server error occurred."),
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "E2", "Invalid parameter."),
  ACCESS_DENIED(HttpStatus.FORBIDDEN, "E3", "Access denied."),

  // Domain Custom Exception
  // SAMPLE
  REFRESH_NOT_VALID(HttpStatus.FORBIDDEN, "RE1", "The refresh token is invalid. redirect to /login"),
  INVALID_PRODUCT_DATA(HttpStatus.BAD_REQUEST, "PROD1", "가격 또는 재고가 음수로 입력되었습니다.")
  ;

  private final HttpStatus status;
  private final String code;
  private String msg;

  public void setCustomMessage(String message) {
    this.msg = message;
  }

  ErrorCode(HttpStatus status, String code, String msg) {
    this.status = status;
    this.code = code;
    this.msg = msg;
  }
}
