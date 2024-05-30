package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.products.exception.ProductNotFoundException;
import com.elice.ggorangjirang.reviews.exception.ReviewNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(ProductNotFoundException ex, Model model) {
        return "error/product-not-found";
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public String handleReviewNotFoundException(ReviewNotFoundException ex, Model model) {
        return "error/review-not-found";
    }
}
