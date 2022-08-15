package com.haisi.java.testfeatures.admin.api;

import com.haisi.java.testfeatures.category.application.CategoryApplicationFacade;
import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryFilter;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import com.haisi.java.testfeatures.utilities.web.dtos.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryAdminControllerTest {

    @Mock
    private CategoryApplicationFacade application;

    @InjectMocks
    private CategoryAdminController controller;

    @Test
    @DisplayName("Find all using paged strategy")
    void findAll_paged() {
        var page = mock(Page.class);

        when(application.findAll(any(), eq(0), eq(1)))
            .thenAnswer(ctx -> {
                CategoryFilter filter = ctx.getArgument(0);
                assertEquals("name", filter.getName().orElse(null), "Wrong filter name");
                return page;
            });

        var result = controller.findAllPageable(0, 1, Optional.of("name"));

        assertSame(page, result, "Wrong result");
    }

    @Test
    @DisplayName("Find all api")
    void findAll() {
        controller.findAll();

        verify(application, times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id api")
    void findById() {
        var found = CategoryResponseDto.builder()
                .id(1L)
                .name("Category")
                .build();

        when(application.findById(1L))
                .thenReturn(found);

        var result = controller.findById(1L);

        assertEquals(found, result, "Wrong dto returned");
    }

    @Test
    @DisplayName("Create new category api")
    void create() {
        var toCreate = CategoryCreateDto.builder()
                .build();
        var created = CategoryResponseDto.builder()
                .id(1L)
                .name("Category")
                .build();

        when(application.create(toCreate))
                .thenReturn(created);

        var result = controller.create(toCreate);

        assertEquals(created, result, "Wrong response");
    }

    @Test
    @DisplayName("Update new category api")
    void update() {
        var toUpdate = CategoryUpdateDto.builder()
            .name("Category")
            .build();
        var updated = CategoryResponseDto.builder()
            .id(1L)
            .name("Category")
            .build();

        when(application.update(toUpdate))
                .thenAnswer(ctx -> {
                    CategoryUpdateDto cat = ctx.getArgument(0);
                    assertEquals(1L, cat.getId(), "Id not defined");
                    return updated;
                });

        var result = controller.update(1L, toUpdate);

        assertEquals(updated, result, "Wrong response");
    }

    @Test
    @DisplayName("Delete a category by id")
    void delete() {
        controller.delete(1L);
        verify(application).delete(1L);
    }

}
