package com.haisi.java.testfeatures.graphql.controller;

import com.haisi.java.testfeatures.category.application.CategoryApplicationFacade;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryGraphQLTest {

    @InjectMocks
    private CategoryGraphQL graphQL;

    @Mock
    private CategoryApplicationFacade applicationFacade;

    @Test
    @DisplayName("find all categories query")
    void allCategories() {
        var category = CategoryResponseDto.builder()
            .name("Category")
            .build();

        when(applicationFacade.findAll())
            .thenReturn(singletonList(category));

        var result = graphQL.allCategories();

        assertEquals(1, result.size(), "Wrong result size");
        assertEquals(category, result.get(0), "Wrong result");
    }

}
