package com.haisi.java.testfeatures.graphql.controller;

import com.haisi.java.testfeatures.category.application.CategoryApplicationFacade;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryGraphQL {

    private final CategoryApplicationFacade applicationFacade;

    @QueryMapping
    public List<CategoryResponseDto> allCategories() {
        return applicationFacade.findAll();
    }

}
