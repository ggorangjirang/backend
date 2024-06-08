package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.discord.DiscordWebhook;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;
import com.elice.ggorangjirang.global.exception.hierachy.common.ProductNotFoundException;
import com.elice.ggorangjirang.global.exception.hierachy.common.ProductOutOfStockException;
import com.elice.ggorangjirang.products.exception.InvalidProductDataException;

import com.elice.ggorangjirang.products.exception.SubcategoryNotFoundException;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

  private final DiscordWebhook discordWebhook;

  @ExceptionHandler({CustomBusinessException.class})
  public ResponseEntity<ErrorResponse> handle(CustomBusinessException e, HttpServletRequest request,
                                              HttpServletResponse response) {
    if (response.isCommitted()) {
        log.warn("Response is already committed for CustomBusinessException: {}", e.getMessage());
        return null;
    }
    String errorMessage = getErrorMessage(e);
    discordWebhook.sendWebhookMessage(errorMessage);

    return ResponseEntity
        .status(e.getErrorCode().getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
  }

  private String getErrorMessage(CustomBusinessException e) {
    return e.getErrorCode().getMsg() + ": " + e.getErrorCode();
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e, HttpServletRequest request,
                                                                      HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for ProductNotFoundException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(ErrorCode.INVALID_PARAMETER.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.INVALID_PARAMETER));
  }

  @ExceptionHandler(ReviewNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleReviewNotFoundException(ReviewNotFoundException e,
    HttpServletRequest request, HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for ReviewNotFoundException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(ErrorCode.INVALID_PARAMETER.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.INVALID_PARAMETER));
  }

  @ExceptionHandler(InvalidProductDataException.class)
  public ResponseEntity<ErrorResponse> handleInvalidProductDataException(InvalidProductDataException e,
    HttpServletRequest request, HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for InvalidProductDataException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(ErrorCode.INVALID_PRODUCT_DATA.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.INVALID_PRODUCT_DATA));
  }

  @ExceptionHandler(SubcategoryNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleSubcategoryNotFoundException(SubcategoryNotFoundException e,
    HttpServletRequest request, HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for SubcategoryNotFoundException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(ErrorCode.INVALID_PARAMETER.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.INVALID_PARAMETER));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handle(Exception e, HttpServletRequest request, HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for Exception: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);

    return ResponseEntity
        .status(ErrorCode.INVALID_PARAMETER.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
  }
}
