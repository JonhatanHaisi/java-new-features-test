package com.haisi.java.testfeatures.category.application;

import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryFilter;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import com.haisi.java.testfeatures.utilities.web.dtos.Page;

import java.util.List;

public interface CategoryApplicationFacade {

    CategoryResponseDto create(final CategoryCreateDto dto);
    CategoryResponseDto update(final CategoryUpdateDto dto);
    void delete(final Long id);
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(final Long id);
    Page<CategoryResponseDto> findAll(CategoryFilter filter, Integer page, Integer size);

}
