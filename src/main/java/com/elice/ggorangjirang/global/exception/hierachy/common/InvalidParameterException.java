package com.elice.ggorangjirang.global.exception.hierachy.common;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;

public class InvalidParameterException extends CustomBusinessException {

  public InvalidParameterException(ErrorCode errorCode) {
    super(errorCode);
  }

  public InvalidParameterException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }
}
