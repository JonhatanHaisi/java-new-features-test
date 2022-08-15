package com.haisi.java.testfeatures.appweb.errorhandling;

import com.haisi.java.testfeatures.utilities.web.dtos.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Log4j2
@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleInternalServerError(Exception e) {
        log.error(e.getMessage(), e);
        var error = ErrorDto.builder()
                .message("Unexpected server error")
                .build();

        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }

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

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<List<ErrorDto>> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(null, NOT_FOUND);
    }

}
