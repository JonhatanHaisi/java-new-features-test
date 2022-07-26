package com.haisi.java.testfeatures.app.errorhandling;

import com.haisi.java.testfeatures.utilities.web.dtos.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log4j2
@ControllerAdvice
public class ServerErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleInternalServerError(Exception e) {
        log.error(e.getMessage(), e);
        var error = ErrorDto.builder()
            .message("Unexpected server error")
            .build();

        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }

}
