package com.haisi.java.testfeatures.category.validations;

import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LocalValidatorFactoryBean.class, CategoryRepository.class})
@ExtendWith(SpringExtension.class)
class ValidateCategoryUpdateDto {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @MockBean
    private CategoryRepository repository;

    @Test
    @DisplayName("Validate a dto using valid parameters")
    void dtoValid() {
        var dto = CategoryUpdateDto.builder()
            .id(1L)
            .name("Valid")
            .build();

        when(repository.findByName(dto.getName()))
            .thenReturn(Optional.of(new CategoryEntity(1L, "Valid", null)));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Violations is not empty");
    }

    @Test
    @DisplayName("Validate a dto using invalid id - null")
    void dtoInvalid_id_null() {
        var dto = CategoryUpdateDto.builder()
                .name(repeat("A", 255))
                .build();

        var violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Wrong number of violations");

        var violation = violations.stream().findFirst().orElse(null);
        assertEquals("must not be null", violation.getMessage(), "Wrong message");
        assertEquals("id", violation.getPropertyPath().toString(), "Wrong property");
    }

    @Test
    @DisplayName("Validate a dto using invalid name length")
    void dtoInvalid_name_maxLength() {
        var dto = CategoryUpdateDto.builder()
            .id(1L)
            .name(repeat("A", 256))
            .build();

        var violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Wrong number of violations");

        var violation = violations.stream().findFirst().orElse(null);
        assertEquals("Field length should be smaller than 255 characters long", violation.getMessage(), "Wrong message");
        assertEquals("name", violation.getPropertyPath().toString(), "Wrong property");
    }

    @Test
    @DisplayName("Validate a dto using invalid name - empty")
    void dtoInvalid_name_empty() {
        var dto = CategoryUpdateDto.builder()
                .id(1L)
                .name("")
                .build();

        var violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Wrong number of violations");

        var violation = violations.stream().findFirst().orElse(null);
        assertEquals("Field should not be empty", violation.getMessage(), "Wrong message");
        assertEquals("name", violation.getPropertyPath().toString(), "Wrong property");
    }

    @Test
    @DisplayName("Validate a dto using invalid name - null")
    void dtoInvalid_name_null() {
        var dto = CategoryUpdateDto.builder()
                .id(1L)
                .name(null)
                .build();

        var violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Wrong number of violations");

        var violation = violations.stream().findFirst().orElse(null);
        assertEquals("Field should not be empty", violation.getMessage(), "Wrong message");
        assertEquals("name", violation.getPropertyPath().toString(), "Wrong property");
    }

    @Test
    @DisplayName("Validate a dto using duplicated name")
    void dtoInvalid_name_duplicated() {
        var dto = CategoryUpdateDto.builder()
            .id(2L)
            .name("Duplicated")
            .build();

        when(repository.findByName(dto.getName()))
            .thenReturn(Optional.of(new CategoryEntity(1L, "Duplicated", null)));

        var violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Wrong number of violations");

        var violation = violations.stream().findFirst().orElse(null);
        assertEquals("Category name is not unique", violation.getMessage(), "Wrong message");
        assertEquals("", violation.getPropertyPath().toString(), "Wrong property");
    }

}
