package com.elice.ggorangjirang.global.exception.hierachy.common;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;

public class AccessDeniedException extends CustomBusinessException {

  public AccessDeniedException(ErrorCode errorCode) {
    super(errorCode);
  }

  public AccessDeniedException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }
}
