package com.haisi.java.testfeatures.category.application.impl;

import com.haisi.java.testfeatures.category.application.CategoryApplicationFacade;
import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import com.haisi.java.testfeatures.utilities.abstractions.CrudModel;
import com.haisi.java.testfeatures.utilities.qualifiers.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Category))
public class CategoryApplicationFacadeImpl implements CategoryApplicationFacade {

    @Category
    private final CrudModel<Long> model;

    public CategoryResponseDto create(final CategoryCreateDto dto) {
        return model.save(dto, CategoryResponseDto.class);
    }

    public CategoryResponseDto update(final CategoryUpdateDto dto) {
        return model.save(dto, CategoryResponseDto.class);
    }

    public List<CategoryResponseDto> findAll() {
        return model.findAll(CategoryResponseDto.class);
    }

    public CategoryResponseDto findById(final Long id) {
        return model.findById(id, CategoryResponseDto.class)
                .orElse(null);
    }

}
