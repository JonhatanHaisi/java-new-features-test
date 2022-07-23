package com.haisi.java.testfeatures.product.mapper;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.entity.ProductEntity;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.product.dtos.ProductUpdateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductUpdateMapperConfigurationTest {

    private ProductUpdateMapperConfiguration mapperConfiguration;
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setup() {
        categoryRepository = mock(CategoryRepository.class);
        var utils = new ProductMapperUtils(categoryRepository);
        mapperConfiguration = new ProductUpdateMapperConfiguration(utils);
    }

    @Test
    @DisplayName("Configuration from ProductUpdateDto to ProductEntity")
    void configure() {
        var categoryEntity = CategoryEntity.builder()
                .id(1L)
                .name("Category")
                .build();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        var toMap = ProductUpdateDto.builder()
                .categories(singletonList(1L))
                .build();

        var modelMapper = new ModelMapper();
        mapperConfiguration.configure(modelMapper);
        var result = modelMapper.map(toMap, ProductEntity.class);

        assertEquals(1, result.getCategories().size(), "Wrong categories size");
        assertEquals(categoryEntity, result.getCategories().get(0), "Wrong mapped category");
    }

}
