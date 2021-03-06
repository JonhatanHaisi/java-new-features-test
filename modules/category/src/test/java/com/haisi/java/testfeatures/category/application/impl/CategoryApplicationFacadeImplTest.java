package com.haisi.java.testfeatures.category.application.impl;

import com.haisi.java.testfeatures.abstractions.CrudModel;
import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryApplicationFacadeImplTest {

    @InjectMocks
    private CategoryApplicationFacadeImpl application;

    @Mock
    private CrudModel<Long> model;

    @Test
    @DisplayName("Create a new category")
    void create() {
        var createDto = CategoryCreateDto
            .builder()
            .name("Category")
            .build();

        var response = CategoryResponseDto
            .builder()
            .id(1L)
            .name("Category")
            .build();

        when(model.save(createDto, CategoryResponseDto.class))
            .thenReturn(response);

        var result = application.create(createDto);

        assertEquals(response, result, "Wrong result");
    }

    @Test
    @DisplayName("Update a category")
    void update() {
        var createDto = CategoryUpdateDto
                .builder()
                .id(1L)
                .name("Category")
                .build();

        var response = CategoryResponseDto
                .builder()
                .id(1L)
                .name("Category")
                .build();

        when(model.save(createDto, CategoryResponseDto.class))
                .thenReturn(response);

        var result = application.update(createDto);

        assertEquals(response, result, "Wrong result");
    }

    @Test
    @DisplayName("Find all categories")
    void findAll() {
        var response = CategoryResponseDto
            .builder()
            .id(1L)
            .name("Category")
            .build();

        when(model.findAll(CategoryResponseDto.class))
                .thenReturn(singletonList(response));

        var result = application.findAll();

        assertEquals(1, result.size(), "Wrong size");
        assertEquals(response, result.get(0), "Wrong result");
    }

    @Test
    @DisplayName("Find a category by id")
    void findById() {
        var response = CategoryResponseDto
            .builder()
            .id(1L)
            .name("Category")
            .build();

        when(model.findById(1L, CategoryResponseDto.class))
            .thenReturn(Optional.of(response));

        var result = application.findById(1L);

        assertEquals(response, result, "Wrong response");
    }

}
