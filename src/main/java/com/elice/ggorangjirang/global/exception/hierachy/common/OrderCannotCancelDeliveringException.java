package com.elice.ggorangjirang.global.exception.hierachy.common;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;

public class OrderCannotCancelDeliveringException extends CustomBusinessException {

  public OrderCannotCancelDeliveringException() {
    super(ErrorCode.ORDER_CANNOT_CANCEL_DELIVERING);
  }

  public OrderCannotCancelDeliveringException(String message) {
    super(ErrorCode.ORDER_CANNOT_CANCEL_DELIVERING, message);
  }
}
