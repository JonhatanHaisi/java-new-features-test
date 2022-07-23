package com.haisi.java.testfeatures.abstractions;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface CrudModel<I> {

    <D, R> R save(@Valid final D dto, final Class<R> returnType);
    <D, R> List<R> saveAll(@Valid final List<D> dtos, final Class<R> returnType);
    <D> void save(@Valid final D dto);
    <D> void saveAll(@Valid final List<D> dtos);

    <D> void delete(@Valid final D dto);
    <D> void deleteAll(@Valid final List<D> dtos);

    void deleteById(final I id);
    void deleteAllById(final List<I> ids);

    <R> List<R> findAll(final Class<R> returnType);
    <R> Optional<R> findById(final I id, final Class<R> returnType);
    <R> List<R> findByIds(final List<I> ids, final Class<R> returnType);

}
