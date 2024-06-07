package com.elice.ggorangjirang.global.exception.hierachy.common;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;


// 상품 추가할때, 데이터베이스 무결성 제약 조건이 위반 알림
public class DataIntegrityViolationException extends CustomBusinessException {
  public DataIntegrityViolationException() {
    super(ErrorCode.DATA_INTEGRITY_VIOLATION);
  }

  public DataIntegrityViolationException(String message) {
    super(ErrorCode.DATA_INTEGRITY_VIOLATION, message);
  }
}
