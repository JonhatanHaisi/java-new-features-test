package com.haisi.java.testfeatures.category.configuration;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import com.haisi.java.testfeatures.utilities.abstractions.CrudModel;
import com.haisi.java.testfeatures.utilities.abstractions.impl.CrudModelImpl;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import com.haisi.java.testfeatures.utilities.qualifiers.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.haisi.java.testfeatures.category")
public class CategorySpringConfiguration {

    @Bean
    @Category
    public CrudModel<Long> categoryCrudModel(CategoryRepository repository, Mapper mapper) {
        return CrudModelImpl.<CategoryEntity, Long>builder()
            .repository(repository)
            .entityType(CategoryEntity.class)
            .mapper(mapper)
            .build();
    }

}
