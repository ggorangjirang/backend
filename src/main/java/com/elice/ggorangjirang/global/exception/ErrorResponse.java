package com.elice.ggorangjirang.global.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

  private final String code;
  private final String msg;

  public ErrorResponse(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.msg = errorCode.getMsg();
  }

  public ErrorResponse(ErrorCode errorCode, String msg) {
    this.code = errorCode.getCode();
    this.msg = msg;
  }
}
