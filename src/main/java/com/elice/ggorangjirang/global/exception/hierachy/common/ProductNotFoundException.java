package com.elice.ggorangjirang.global.exception.hierachy.common;

import com.elice.ggorangjirang.global.exception.ErrorCode;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;

public class ProductNotFoundException extends CustomBusinessException {
  public ProductNotFoundException() {
    super(ErrorCode.PRODUCT_NOT_FOUND);
  }
}

