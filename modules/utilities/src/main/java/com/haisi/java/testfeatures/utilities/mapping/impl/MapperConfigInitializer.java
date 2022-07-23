package com.haisi.java.testfeatures.utilities.mapping.impl;

import com.haisi.java.testfeatures.utilities.mapping.MapperConfigurator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
public class MapperConfigInitializer {

    private final ModelMapper mapper;
    private final List<MapperConfigurator> configs;

    @PostConstruct
    public void setupMappers() {
        configs.forEach(it -> it.configure(mapper));
    }

}
