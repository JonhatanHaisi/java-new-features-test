package com.haisi.java.testfeatures.utilities.mapping.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MapperImplTest {

    @InjectMocks
    private MapperImpl mapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    @DisplayName("Map an object")
    void map() {
        var toMap = "to map";
        var mapped = "mapped";

        when(modelMapper.map(toMap, String.class))
                .thenReturn(mapped);

        var result = mapper.map(toMap, String.class);

        assertEquals(mapped, result, "Wrong result");
    }

    @Test
    @DisplayName("Map a list of objects")
    void mapAll() {
        var toMap = "to map";
        var mapped = "mapped";

        when(modelMapper.map(toMap, String.class))
                .thenReturn(mapped);

        var result = mapper.mapAll(singletonList(toMap), String.class);

        assertEquals(1, result.size(), "Wrong size");
        assertEquals(mapped, result.get(0), "Wrong result");
    }

}
