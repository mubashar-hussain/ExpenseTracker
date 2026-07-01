package com.mubashar.expensetracker.exceptions;

import com.mubashar.expensetracker.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ApiError> handleException(RuntimeException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(LocalDateTime.now(), status.name(), ex.getMessage(), status.value(), req.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryException(RuntimeException ex, HttpServletRequest req) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(LocalDateTime.now(), status.name(), ex.getMessage(), status.value(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest req) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(LocalDateTime.now(),
                status.name(),
                ex.getMessage(),
                status.value(),
                req.getRequestURI());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);


    }


}
