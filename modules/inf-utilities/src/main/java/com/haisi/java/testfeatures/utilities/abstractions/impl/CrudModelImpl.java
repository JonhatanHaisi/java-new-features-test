package com.haisi.java.testfeatures.utilities.abstractions.impl;

import com.haisi.java.testfeatures.utilities.abstractions.CrudModel;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Builder
@Validated
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CrudModelImpl<E, I> implements CrudModel<I> {

    private final CrudRepository<E, I> repository;
    private final Class<E> entityType;
    private final Mapper mapper;

    @Override
    @Transactional
    public <D, R> R save(@Valid final D dto, final Class<R> returnType) {
        final var entity = this.mapper.map(dto, entityType);
        final var saved = this.repository.save(entity);
        return this.mapper.map(saved, returnType);
    }

    @Override
    @Transactional
    public <D, R> List<R> saveAll(@Valid final List<D> dtos, final Class<R> returnType) {
        final var entities = this.mapper.mapAll(dtos, entityType);
        final var saved = (List<E>) this.repository.saveAll(entities);
        return this.mapper.mapAll(saved, returnType);
    }

    @Override
    @Transactional
    public <D> void save(@Valid final D dto) {
        final var entity = this.mapper.map(dto, entityType);
        this.repository.save(entity);
    }

    @Override
    @Transactional
    public <D> void saveAll(@Valid final List<D> dtos) {
        final var entities = this.mapper.mapAll(dtos, entityType);
        this.repository.saveAll(entities);
    }

    @Override
    @Transactional
    public <D> void delete(@Valid final D dto) {
        final var entity = this.mapper.map(dto, entityType);
        this.repository.delete(entity);
    }

    @Override
    @Transactional
    public <D> void deleteAll(@Valid final List<D> dtos) {
        final var entities = this.mapper.mapAll(dtos, entityType);
        this.repository.deleteAll(entities);
    }

    @Override
    @Transactional
    public void deleteById(final I id) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllById(final List<I> ids) {
        this.repository.deleteAllById(ids);
    }

    @Override
    public <R> List<R> findAll(final Class<R> returnType) {
        var entities = (List<E>) this.repository.findAll();
        return this.mapper.mapAll(entities, returnType);
    }

    @Override
    public <R> Optional<R> findById(final I id, final Class<R> returnType) {
        var entity = this.repository.findById(id);
        return entity.map(e -> this.mapper.map(e, returnType));
    }

    @Override
    public <R> List<R> findByIds(final List<I> ids, final Class<R> returnType) {
        var entities = (List<E>) this.repository.findAllById(ids);
        return this.mapper.mapAll(entities, returnType);
    }

}
