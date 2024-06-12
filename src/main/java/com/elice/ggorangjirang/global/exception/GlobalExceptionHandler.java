package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.discord.DiscordWebhook;
import com.elice.ggorangjirang.global.exception.hierachy.CustomBusinessException;
import com.elice.ggorangjirang.global.exception.hierachy.common.EmailNotVerfiedException;
import com.elice.ggorangjirang.global.exception.hierachy.common.ProductNotFoundException;
import com.elice.ggorangjirang.global.exception.hierachy.common.ProductOutOfStockException;
import com.elice.ggorangjirang.products.exception.InvalidProductDataException;

import com.elice.ggorangjirang.products.exception.SubcategoryNotFoundException;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException e, HttpServletRequest request,
                                                       HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for handleRuntimeException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body("유저를 찾을 수 없습니다.");
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

  @ExceptionHandler(EmailNotVerfiedException.class)
  public ResponseEntity<ErrorResponse> handleEmailNotVerfiedException(EmailNotVerfiedException e,
        HttpServletRequest request, HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for handleEmailNotVerfiedException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(ErrorCode.EMAIL_NOT_VERIFIED.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.EMAIL_NOT_VERIFIED));
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request,
                                                                     HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for BadCredentialsException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(ErrorCode.BAD_CREDENTIALS.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.BAD_CREDENTIALS));
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request,
                                                                       HttpServletResponse response) {
    if (response.isCommitted()) {
      log.warn("Response is already committed for UsernameNotFoundException: {}", e.getMessage());
      return null;
    }

    log.error("error: {}", e);
    return ResponseEntity
        .status(ErrorCode.USER_NOT_FOUND.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(ErrorCode.USER_NOT_FOUND));
  }
}
