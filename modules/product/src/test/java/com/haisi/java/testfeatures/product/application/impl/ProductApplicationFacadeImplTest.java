package com.haisi.java.testfeatures.product.application.impl;

import com.haisi.java.testfeatures.abstractions.CrudModel;
import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.product.dtos.ProductResponseDto;
import com.haisi.java.testfeatures.product.dtos.ProductUpdateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductApplicationFacadeImplTest {

    @InjectMocks
    private ProductApplicationFacadeImpl application;

    @Mock
    private CrudModel<Long> crudModel;

    @Test
    @DisplayName("Create a new product")
    void create() {
        var toCreate = ProductCreateDto.builder()
            .name("Category")
            .description("Description")
            .build();
            
        var created = ProductResponseDto.builder()
            .id(1L)
            .name("Category")
            .description("Description")
            .build();

        when(crudModel.save(toCreate, ProductResponseDto.class))
                .thenReturn(created);
            
        var result = application.create(toCreate);

        assertEquals(created, result, "Wrong result");
    }

    @Test
    @DisplayName("Update a product")
    void update() {
        var toUpdate = ProductUpdateDto.builder()
            .id(1L)
            .name("Category")
            .description("Description")
            .build();

        var updated = ProductResponseDto.builder()
            .id(1L)
            .name("Category")
            .description("Description")
            .build();

        when(crudModel.save(toUpdate, ProductResponseDto.class))
                .thenReturn(updated);

        var result = application.update(toUpdate);

        assertEquals(updated, result, "Wrong result");
    }

    @Test
    @DisplayName("List all products")
    void findAll() {
        var found = ProductResponseDto.builder()
            .id(1L)
            .name("Category")
            .description("Description")
            .build();

        when(crudModel.findAll(ProductResponseDto.class))
            .thenReturn(Collections.singletonList(found));

        var result = application.findAll();

        assertEquals(1, result.size(), "Wrong size");
        assertEquals(found, result.get(0), "Wrong result");
    }

    @Test
    @DisplayName("Find a product by id")
    void findById() {
        var found = ProductResponseDto.builder()
                .id(1L)
                .name("Category")
                .description("Description")
                .build();

        when(crudModel.findById(1L, ProductResponseDto.class))
                .thenReturn(Optional.of(found));

        var result = application.findById(1L);

        assertEquals(found, result, "Wrong result");
    }

}
