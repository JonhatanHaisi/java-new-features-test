package com.haisi.java.testfeatures.appweb.errorhandling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ControllerErrorHandlerTest {

    @InjectMocks
    private ControllerErrorHandler errorHandler;

    @Test
    @DisplayName("Test the error handling for validation errors")
    void serverError() {
        var error = mock(ConstraintViolationException.class);
        var violation = mock(ConstraintViolation.class);
        var propertyPath = mock(Path.class);

        when(propertyPath.toString())
                .thenReturn("the.property");
        when(violation.getPropertyPath())
                .thenReturn(propertyPath);
        when(violation.getMessage())
                .thenReturn("validation message");

        when(error.getConstraintViolations())
                .thenReturn(Set.of(violation));

        var result = errorHandler.handleValidationExceptions(error);

        assertNotNull(result.getBody(), "Error dto not generated");
        assertEquals(1, result.getBody().size(), "Wrong error list size");
        assertEquals("the.property", result.getBody().get(0).getField(), "Wrong error field");
        assertEquals("validation message", result.getBody().get(0).getMessage(), "Wrong validation message");
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode(), "Wrong http error code");
    }

    @Test
    @DisplayName("Test the error handling for unexpected errors")
    void testHandleInternalServerError() {
        var error = new Exception("Error Message");

        var result = errorHandler.handleInternalServerError(error);

        assertNotNull(result.getBody(), "Error dto not generated");
        assertEquals("Unexpected server error", result.getBody().getMessage(), "Unexpected server error");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode(), "Wrong http error code");
    }

}
