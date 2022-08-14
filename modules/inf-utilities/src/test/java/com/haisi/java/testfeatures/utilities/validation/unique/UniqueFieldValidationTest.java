package com.haisi.java.testfeatures.utilities.validation.unique;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniqueFieldValidationTest {

    @Test
    @DisplayName("Valid object without id")
    void isValid_valid_without_id() {
        var validator = new Validator(field -> Optional.empty());
        assertTrue(validator.isValid(() -> "Unique"));
    }

    @Test
    @DisplayName("Invalid object without id")
    void isValid_invalid_without_id() {
        var validator = new Validator(field -> Optional.of("Unique"));
        assertFalse(validator.isValid(() -> "Non unique"));
    }

    @Test
    @DisplayName("Valid object with id")
    void isValid_valid_with_id() {
        var validator = new Validator(field -> Optional.of("Unique"));
        assertTrue(validator.isValid("Unique"::toString, "Unique"::hashCode));
    }

    @Test
    @DisplayName("Invalid object with id")
    void isValid_invalid_with_id() {
        var validator = new Validator(field -> Optional.of("Unique"));
        assertFalse(validator.isValid("Non unique"::toString, "Non unique"::hashCode));
    }


    public static class Validator extends UniqueFieldValidation<String, String, Integer> {
        public Validator(Function<String, Optional<String>> repository) {
            super(repository, String::hashCode);
        }
    }

}
