package com.elice.ggorangjirang.global.exception.hierachy;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomBusinessException extends RuntimeException{

  private final ErrorCode errorCode;

  public CustomBusinessException(ErrorCode errorCode) {
    super(errorCode.getMsg());
    this.errorCode = errorCode;
  }

  public CustomBusinessException(ErrorCode errorCode, String message) {
    super(message);
    errorCode.setCustomMessage(message);
    this.errorCode = errorCode;
  }
}
