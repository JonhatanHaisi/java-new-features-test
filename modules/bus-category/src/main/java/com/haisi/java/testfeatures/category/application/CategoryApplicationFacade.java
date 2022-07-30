package com.haisi.java.testfeatures.category.application;

import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;

import java.util.List;

public interface CategoryApplicationFacade {

    CategoryResponseDto create(final CategoryCreateDto dto);
    CategoryResponseDto update(final CategoryUpdateDto dto);
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(final Long id);

}
