package com.haisi.java.testfeatures.category.application.impl;

import com.haisi.java.testfeatures.category.application.CategoryApplicationFacade;
import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryFilter;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import com.haisi.java.testfeatures.category.model.CategoryPageableSearch;
import com.haisi.java.testfeatures.utilities.abstractions.CrudModel;
import com.haisi.java.testfeatures.utilities.qualifiers.Category;
import com.haisi.java.testfeatures.utilities.web.dtos.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Category))
public class CategoryApplicationFacadeImpl implements CategoryApplicationFacade {

    private final CategoryPageableSearch pageableSearch;
    private final CrudModel<Long> model;

    public CategoryResponseDto create(final CategoryCreateDto dto) {
        return model.save(dto, CategoryResponseDto.class);
    }

    public CategoryResponseDto update(final CategoryUpdateDto dto) {
        return model.save(dto, CategoryResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        model.deleteById(id);
    }

    public List<CategoryResponseDto> findAll() {
        return model.findAll(CategoryResponseDto.class);
    }

    public CategoryResponseDto findById(final Long id) {
        return model.findById(id, CategoryResponseDto.class)
                .orElse(null);
    }

    @Override
    public Page<CategoryResponseDto> findAll(CategoryFilter filter, Integer page, Integer size) {
        return pageableSearch.findAll(filter, page, size, CategoryResponseDto.class);
    }

}
