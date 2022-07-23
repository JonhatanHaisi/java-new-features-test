package com.haisi.java.testfeatures.utilities.mapping.impl;

import com.haisi.java.testfeatures.utilities.mapping.MapperConfigurator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

class MapperConfigInitializerTest {

    @Test
    @DisplayName("Test the mappers initialization")
    void setupMappers() {
        var mapper = mock(ModelMapper.class);
        var mapperConfigurator = mock(MapperConfigurator.class);

        var configInitializer = new MapperConfigInitializer(mapper, singletonList(mapperConfigurator));
        configInitializer.setupMappers();

        verify(mapperConfigurator, times(1)).configure(mapper);
    }

}
