package com.haisi.java.testfeatures.utilities.abstractions;

import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import com.haisi.java.testfeatures.utilities.web.dtos.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class PageableSearch<F, E> {

    private final JpaSpecificationExecutor<E> repository;
    private final Mapper mapper;

    public <R> Page<R> findAll(F filter, Integer page, Integer size, Class<R> resultFormat) {
        var pageable = createSort(filter)
            .map(s -> PageRequest.of(page, size, s))
            .orElseGet(() -> PageRequest.of(page, size));

        var result = repository.findAll(createFilter(filter), pageable);
        var mapped = mapper.mapAll(result.getContent(), resultFormat);

        return Page.of(mapped, pageable, result.getTotalElements());
    }

    protected abstract Specification<E> createFilter(F filter);
    protected abstract Optional<Sort> createSort(F filter);
}
