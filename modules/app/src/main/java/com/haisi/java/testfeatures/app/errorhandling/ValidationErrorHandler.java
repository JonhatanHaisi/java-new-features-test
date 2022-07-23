package com.haisi.java.testfeatures.app.errorhandling;

import com.haisi.java.testfeatures.utilities.web.dtos.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ValidationErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorDto>> handleValidationExceptions(ConstraintViolationException ex) {
        var errors = ex.getConstraintViolations()
                .stream()
                .map(e ->
                    ErrorDto.builder()
                        .field(e.getPropertyPath().toString())
                        .message(e.getMessage())
                        .build()
                ).toList();

        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

}
