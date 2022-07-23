package com.haisi.java.testfeatures.utilities.mapping.impl;

import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MapperImpl implements Mapper {

    private final ModelMapper mapper;

    public <R> R map(final Object obj, Class<R> returnType) {
        return this.mapper.map(obj, returnType);
    }

    public <R> List<R> mapAll(final List<? extends Object> objs, Class<R> returnType) {
        return objs.stream()
                .map(o -> this.mapper.map(o, returnType))
                .toList();
    }

}
