package com.haisi.java.testfeatures.product.mapper;

import com.haisi.java.testfeatures.data.entity.ProductEntity;
import com.haisi.java.testfeatures.product.dtos.ProductCreateDto;
import com.haisi.java.testfeatures.utilities.mapping.MapperConfigurator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductCreateMapperConfiguration implements MapperConfigurator {

    private final ProductMapperUtils productMapperUtils;

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.typeMap(ProductCreateDto.class, ProductEntity.class)
            .addMappings(mapper ->
                mapper.using(productMapperUtils.convertIdToCategory())
                    .map(ProductCreateDto::getCategories, ProductEntity::setCategories)
            );
    }
}
