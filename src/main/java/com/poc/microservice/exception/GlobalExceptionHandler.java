package com.poc.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UnnaxCatalogNotFoundException.class, UnnaxTransactionNotFoundException.class})
    public ResponseEntity<CecobanErrorResponseDto> handleResourceNotFoundException(
            RuntimeException exception, HttpServletRequest request) {

        CecobanErrorResponseDto errorResponse = new CecobanErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnnaxCatalogMismatchException.class, UnnaxTransactionMismatchException.class})
    public ResponseEntity<CecobanErrorResponseDto> handleAlreadyExistsExceptionException(
            RuntimeException exception, HttpServletRequest request) {

        CecobanErrorResponseDto errorResponse = new CecobanErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CecobanErrorResponseDto> handleGlobalException(
            Exception exception, HttpServletRequest request) {

        CecobanErrorResponseDto errorResponse = new CecobanErrorResponseDto(
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected internal server error occurred. Please try again later.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}