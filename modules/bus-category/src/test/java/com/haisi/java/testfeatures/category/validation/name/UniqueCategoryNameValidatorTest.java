package com.haisi.java.testfeatures.category.validation.name;

import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UniqueCategoryNameValidatorTest {

    @InjectMocks
    private UniqueCategoryNameValidator validator;

    @Mock
    private CategoryRepository repository;

    @Test
    @DisplayName("An object without id or name is valid")
    void isValid_valid_black() {
        var result = validator.isValid(new CategoryCreateDto(), null);

        assertTrue(result, "Wrong validation result");
    }

    @Test
    @DisplayName("An object without id is valid")
    void isValid_valid_withoutId() {
        when(repository.findByName("name")).thenReturn(Optional.empty());

        var result = validator.isValid(new CategoryCreateDto("name"), null);

        assertTrue(result, "Wrong validation result");
    }

    @Test
    @DisplayName("An object with id is valid")
    void isValid_valid_withId() {
        when(repository.findByName("name")).thenReturn(Optional.empty());

        var result = validator.isValid(new CategoryUpdateDto(1L, "name"), null);

        assertTrue(result, "Wrong validation result");
    }

    @Test
    @DisplayName("An object with id is valid - Update operations")
    void isValid_valid_withId_update() {
        var found = Optional.of(new CategoryEntity(1L, "name", emptyList()));
        when(repository.findByName("name")).thenReturn(found);

        var result = validator.isValid(new CategoryUpdateDto(1L, "name"), null);

        assertTrue(result, "Wrong validation result");
    }

    @Test
    @DisplayName("An object without id is invalid")
    void isValid_invalid_withoutId() {
        var found = Optional.of(new CategoryEntity(1L, "name", emptyList()));
        when(repository.findByName("name")).thenReturn(found);

        var result = validator.isValid(new CategoryCreateDto("name"), null);

        assertFalse(result, "Wrong validation result");
    }

    @Test
    @DisplayName("An object with id is invalid")
    void isValid_invalid_withId() {
        var found = Optional.of(new CategoryEntity(2L, "name", emptyList()));
        when(repository.findByName("name")).thenReturn(found);

        var result = validator.isValid(new CategoryUpdateDto(1L, "name"), null);

        assertFalse(result, "Wrong validation result");
    }

}
