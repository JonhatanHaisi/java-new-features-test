package com.haisi.java.testfeatures.admin.api;

import com.haisi.java.testfeatures.category.application.CategoryApplicationFacade;
import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryResponseDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryAdminController {

    private final CategoryApplicationFacade application;

    @GetMapping
    public List<CategoryResponseDto> findAll() {
        return application.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return application.findById(id);
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryCreateDto dto) {
        return application.create(dto);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id, @RequestBody CategoryUpdateDto dto) {
        dto.setId(id);
        return application.update(dto);
    }

}
