package com.haisi.java.testfeatures.product.application;

import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.product.dtos.ProductResponseDto;
import com.haisi.java.testfeatures.product.dtos.ProductUpdateDto;

import java.util.List;

public interface ProductApplicationFacade {

    ProductResponseDto create(ProductCreateDto dto);
    ProductResponseDto update(ProductUpdateDto dto);
    List<ProductResponseDto> findAll();
    ProductResponseDto findById(Long id);

}
