package com.elice.ggorangjirang.global.exception.domain.sample;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.common.AccessDeniedException;

public class RefreshTokenNotValidException extends AccessDeniedException {

  public RefreshTokenNotValidException(ErrorCode errorCode) {
    super(errorCode);
  }

  public RefreshTokenNotValidException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }
}
