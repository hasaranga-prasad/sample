package com.example.sample.config;

import com.example.sample.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@Hidden
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        if (fieldErrors.isEmpty()) {
            return new ResponseEntity<>(
                    new ResponseDTO(400, "No validation errors found", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        FieldError firstError = fieldErrors.get(0);
        String errorMessage = firstError.getField() + ": " + firstError.getDefaultMessage();

        return new ResponseEntity<>(
                new ResponseDTO(400, errorMessage, null),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                new ResponseDTO(500, "An unexpected error occurred: " + ex.getMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
