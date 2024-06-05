package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.discord.DiscordWebhook;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;
import com.elice.ggorangjirang.products.exception.InvalidProductDataException;
import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.products.exception.SubcategoryNotFoundException;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

  private final DiscordWebhook discordWebhook;

  @ExceptionHandler({CustomBusinessException.class})
  public ResponseEntity<ErrorResponse> handle(CustomBusinessException e) {
    String errorMessage = "Exception occurred: " + e.getMessage();
    discordWebhook.sendErrorMessage(errorMessage);

    return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getMessage()), e.getErrorCode().getStatus());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
    log.error("error: {}", e);
    return new ResponseEntity<>(
            new ErrorResponse(ErrorCode.INVALID_PARAMETER),
            ErrorCode.INVALID_PARAMETER.getStatus());
  }

  @ExceptionHandler(ReviewNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleReviewNotFoundException(ReviewNotFoundException e) {
    log.error("error: {}", e);
    return new ResponseEntity<>(
            new ErrorResponse(ErrorCode.INVALID_PARAMETER),
            ErrorCode.INVALID_PARAMETER.getStatus());
  }

  @ExceptionHandler(InvalidProductDataException.class)
  public ResponseEntity<ErrorResponse> handleInvalidProductDataException(InvalidProductDataException e) {
    log.error("error: {}", e);
    return new ResponseEntity<>(
            new ErrorResponse(ErrorCode.INVALID_PRODUCT_DATA),
            ErrorCode.INVALID_PRODUCT_DATA.getStatus());
  }

  @ExceptionHandler(SubcategoryNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleSubcategoryNotFoundException(SubcategoryNotFoundException e) {
    log.error("error: {}", e);
    return new ResponseEntity<>(
            new ErrorResponse(ErrorCode.INVALID_PARAMETER),
            ErrorCode.INVALID_PARAMETER.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handle(Exception e) {
    log.error("error: {}", e);

    return new ResponseEntity<>(
        new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR),
        ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
  }
}
