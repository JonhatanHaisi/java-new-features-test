package com.haisi.java.testfeatures.graphql.controller;

import com.haisi.java.testfeatures.product.application.ProductApplicationFacade;
import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.product.dtos.ProductResponseDto;
import com.haisi.java.testfeatures.product.dtos.ProductUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductGraphQL {

    private final ProductApplicationFacade applicationFacade;

    @QueryMapping
    public List<ProductResponseDto> allProduct() {
        return applicationFacade.findAll();
    }

    @MutationMapping
    public ProductResponseDto createProduct(@Argument("product") ProductCreateDto dto) {
        return applicationFacade.create(dto);
    }

    @MutationMapping
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto dto) {
        dto.setId(id);
        return applicationFacade.update(dto);
    }

}
