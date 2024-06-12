package com.elice.ggorangjirang.global.exception.hierachy.common;


import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;

public class OrderCannotCancelDeliveredException extends CustomBusinessException {

  public OrderCannotCancelDeliveredException() {
    super(ErrorCode.ORDER_CANNOT_CANCEL_DELIVERED);
  }

  public OrderCannotCancelDeliveredException(String message) {
    super(ErrorCode.ORDER_CANNOT_CANCEL_DELIVERED, message);
  }
}

