package com.haisi.java.testfeatures.graphql.controller;

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

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductGraphQLTest {

    @InjectMocks
    private ProductGraphQL graphQL;

    @Mock
    private ProductApplicationFacade applicationFacade;

    @Test
    @DisplayName("find all products query")
    void allProduct() {
        var product = ProductResponseDto.builder()
            .name("Product")
            .build();

        when(applicationFacade.findAll())
            .thenReturn(singletonList(product));

        var result = graphQL.allProduct();

        assertEquals(1, result.size(), "Wrong result size");
        assertEquals(product, result.get(0), "Wrong result");
    }

    @Test
    @DisplayName("create new product mutation")
    void createProduct() {
        var created = ProductResponseDto.builder()
            .id(1L)
            .name("Product")
            .build();

        var toCreate = ProductCreateDto.builder()
            .name("Product")
            .build();

        when(applicationFacade.create(toCreate))
            .thenReturn(created);

        var result = graphQL.createProduct(toCreate);

        assertEquals(created, result, "Wrong result");
    }

    @Test
    @DisplayName("update product mutation")
    void updateProduct() {
        var created = ProductResponseDto.builder()
            .id(1L)
            .name("Product")
            .build();

        var toUpdate = ProductUpdateDto.builder()
            .name("Product")
            .build();

        when(applicationFacade.update(toUpdate))
                .thenAnswer(ctx -> {
                    ProductUpdateDto prod = ctx.getArgument(0);
                    assertEquals(1L, prod.getId(), "Id not defined");
                    return created;
                });

        var result = graphQL.updateProduct(1L, toUpdate);

        assertEquals(created, result, "Wrong result");
    }

}
