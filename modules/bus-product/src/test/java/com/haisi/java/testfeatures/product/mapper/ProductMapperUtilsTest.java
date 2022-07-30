package com.haisi.java.testfeatures.product.mapper;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.spi.MappingContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductMapperUtilsTest {

    @InjectMocks
    private ProductMapperUtils mapperUtils;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Convert id to category, null source")
    void convertIdToCategory_null_source() {
        var ctx = mock(MappingContext.class);

        var converter = mapperUtils.convertIdToCategory();
        var  result = converter.convert(ctx);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result should be empty");
    }

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Convert id to category, empty source")
    void convertIdToCategory_empty_source() {
        var ctx = mock(MappingContext.class);

        when(ctx.getSource())
            .thenReturn(emptyList());

        var converter = mapperUtils.convertIdToCategory();
        var  result = converter.convert(ctx);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result should be empty");
    }

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Convert id to category")
    void convertIdToCategory() {
        var ctx = mock(MappingContext.class);

        var categoryEntity = CategoryEntity.builder()
            .id(1L)
            .name("Category")
            .build();

        when(ctx.getSource()).thenReturn(singletonList(1L));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        var converter = mapperUtils.convertIdToCategory();
        var  result = converter.convert(ctx);

        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should be empty");
        assertEquals(categoryEntity, result.get(0), "Wrong result");
    }

}

