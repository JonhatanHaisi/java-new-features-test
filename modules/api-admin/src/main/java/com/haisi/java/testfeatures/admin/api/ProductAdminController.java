package com.haisi.java.testfeatures.admin.api;

import com.haisi.java.testfeatures.product.application.ProductApplicationFacade;
import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.product.dtos.ProductResponseDto;
import com.haisi.java.testfeatures.product.dtos.ProductUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductAdminController {

    private final ProductApplicationFacade application;

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return application.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id) {
        return application.findById(id);
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductCreateDto dto) {
        return application.create(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductUpdateDto dto) {
        dto.setId(id);
        return application.update(dto);
    }

}
