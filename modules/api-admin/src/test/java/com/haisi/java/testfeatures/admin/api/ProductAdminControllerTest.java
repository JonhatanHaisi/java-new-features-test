package com.haisi.java.testfeatures.admin.api;

import com.haisi.java.testfeatures.product.application.ProductApplicationFacade;
import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.product.dtos.ProductResponseDto;
import com.haisi.java.testfeatures.product.dtos.ProductUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductAdminControllerTest {

    @Mock
    private ProductApplicationFacade application;

    @InjectMocks
    private ProductAdminController controller;

    @Test
    @DisplayName("Find all api")
    void findAll() {
        controller.findAll();

        verify(application, times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id api")
    void findById() {
        var found = ProductResponseDto.builder()
                .id(1L)
                .name("Product")
                .description("Description")
                .build();

        when(application.findById(1L))
                .thenReturn(found);

        var result = controller.findById(1L);

        assertEquals(found, result, "Wrong dto returned");
    }

    @Test
    @DisplayName("Create new category api")
    void create() {
        var toCreate = ProductCreateDto.builder()
                .build();
        var created = ProductResponseDto.builder()
                .id(1L)
                .name("Product")
                .description("Description")
                .build();

        when(application.create(toCreate))
                .thenReturn(created);

        var result = controller.create(toCreate);

        assertEquals(created, result, "Wrong response");
    }

    @Test
    @DisplayName("Update new category api")
    void update() {
        var toUpdate = ProductUpdateDto.builder()
            .name("Product")
            .build();
        var updated = ProductResponseDto.builder()
            .id(1L)
            .name("Product")
            .description("Description")
            .build();

        when(application.update(toUpdate))
                .thenAnswer(ctx -> {
                    ProductUpdateDto prod = ctx.getArgument(0);
                    assertEquals(1L, prod.getId(), "Id not defined");
                    return updated;
                });

        var result = controller.update(1L, toUpdate);

        assertEquals(updated, result, "Wrong response");
    }

}
