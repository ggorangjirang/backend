package com.elice.ggorangjirang.global.exception.hierachy.common;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;

public class ProductOutOfStockException extends CustomBusinessException {
  public ProductOutOfStockException() {
    super(ErrorCode.PRODUCT_OUT_OF_STOCK);
  }
}

