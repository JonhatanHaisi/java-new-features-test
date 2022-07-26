package com.haisi.java.testfeatures.app.errorhandling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ServerErrorHandlerTest {

    @InjectMocks
    private ServerErrorHandler errorHandler;

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
