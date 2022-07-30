package com.haisi.java.testfeatures.utilities.mapping;

import java.util.List;

public interface Mapper {

    <R> R map(final Object obj, Class<R> returnType);
    <R> List<R> mapAll(final List<? extends Object> objs, Class<R> returnType);

}
