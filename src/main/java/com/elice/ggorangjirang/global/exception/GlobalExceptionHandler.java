package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public String handleProductNotFoundException(ProductNotFoundException ex, Model model) {
    return "error/product-not-found";
  }

  @ExceptionHandler(ReviewNotFoundException.class)
  public String handleReviewNotFoundException(ReviewNotFoundException ex, Model model) {
    return "error/review-not-found";
  }

  @ExceptionHandler({CustomBusinessException.class})
  public ResponseEntity<ErrorResponse> handle(CustomBusinessException e) {

    return new ResponseEntity<>(new ErrorResponse(e.getErrorCode()), e.getErrorCode().getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handle(Exception e) {
    log.error("error: {}", e);

    return new ResponseEntity<>(
        new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR),
        ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
  }
}
