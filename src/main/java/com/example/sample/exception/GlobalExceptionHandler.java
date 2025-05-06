package com.example.sample.exception;

import com.example.sample.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleStudentNotFoundException(StudentNotFoundException ex) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(404)
                .message(ex.getMessage())
                .data(null)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseDTO> handleDatabaseException(DataAccessException ex) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(500)
                .message("Database error occurred: " + ex.getMessage())
                .data(null)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> handlePhoneNumberAlreadyExistsException(PhoneNumberAlreadyExistsException ex) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(400)
                .message(ex.getMessage())
                .data(null)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(400)
                .message(ex.getMessage())
                .data(null)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(500)
                .message("An unexpected error occurred: " + ex.getMessage())
                .data(null)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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
}
