package com.vendorloginservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAppException.class)
    public ResponseEntity<StatusHandler> handleInvalidApp(InvalidAppException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StatusHandler("400", ex.getMessage()));
    }

    @ExceptionHandler(TokenSaveException.class)
    public ResponseEntity<StatusHandler> handleTokenSave(TokenSaveException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StatusHandler("500", "Token persistence failed: " + ex.getMessage()));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<StatusHandler> handleAppException(AppException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StatusHandler("500", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StatusHandler> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StatusHandler("500", "Unexpected error occurred"));
    }
}
