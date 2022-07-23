package com.haisi.java.testfeatures.product.configuration;

import com.haisi.java.testfeatures.abstractions.CrudModel;
import com.haisi.java.testfeatures.abstractions.impl.CrudModelImpl;
import com.haisi.java.testfeatures.data.entity.ProductEntity;
import com.haisi.java.testfeatures.data.repository.ProductRepository;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import com.haisi.java.testfeatures.utilities.qualifiers.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeansConfiguration {

    @Bean
    @Product
    public CrudModel<Long> productCrudModel(final ProductRepository repository, final Mapper mapper) {
        return CrudModelImpl.<ProductEntity, Long>builder()
            .repository(repository)
            .entityType(ProductEntity.class)
            .mapper(mapper)
            .build();
    }

}
