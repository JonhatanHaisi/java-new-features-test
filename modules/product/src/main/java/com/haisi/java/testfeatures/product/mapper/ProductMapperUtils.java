package com.haisi.java.testfeatures.product.mapper;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ProductMapperUtils {

    private final CategoryRepository categoryRepository;

    Converter<List<Long>, List<CategoryEntity>> convertIdToCategory() {
        return ctx -> {
            if(Objects.isNull(ctx.getSource()) || ctx.getSource().isEmpty()) {
                return Collections.emptyList();
            }

            return ctx.getSource()
                    .stream()
                    .map(categoryRepository::findById)
                    .map(opt -> opt.orElse(null))
                    .toList();
        };
    }

}
