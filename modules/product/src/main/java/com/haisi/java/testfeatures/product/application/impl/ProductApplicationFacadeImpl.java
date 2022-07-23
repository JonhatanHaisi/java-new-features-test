package com.haisi.java.testfeatures.product.application.impl;

import com.haisi.java.testfeatures.abstractions.CrudModel;
import com.haisi.java.testfeatures.product.application.ProductApplicationFacade;
import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.product.dtos.ProductResponseDto;
import com.haisi.java.testfeatures.product.dtos.ProductUpdateDto;
import com.haisi.java.testfeatures.utilities.qualifiers.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Product))
public class ProductApplicationFacadeImpl implements ProductApplicationFacade {

    @Product
    private final CrudModel<Long> crudModel;

    public ProductResponseDto create(ProductCreateDto dto) {
        return crudModel.save(dto, ProductResponseDto.class);
    }

    public ProductResponseDto update(ProductUpdateDto dto) {
        return crudModel.save(dto, ProductResponseDto.class);
    }

    public List<ProductResponseDto> findAll() {
        return crudModel.findAll(ProductResponseDto.class);
    }

    public ProductResponseDto findById(Long id) {
        return crudModel.findById(id, ProductResponseDto.class)
                .orElse(null);
    }

}
