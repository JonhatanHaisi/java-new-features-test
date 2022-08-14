package com.haisi.java.testfeatures.utilities.validation.unique;

import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public abstract class UniqueFieldValidation<E, F, I> {

    private final Function<F, Optional<E>> repository;
    private final Function<E, I> entityIdGetter;

    protected boolean isValid(Supplier<F> uniqueField, Supplier<I> idField) {
        var id = idField.get();
        return repository.apply(uniqueField.get())
            .map(entityIdGetter)
            .filter(entityId -> isNull(id) || !Objects.equals(id, entityId))
            .isEmpty();
    }

    protected boolean isValid(Supplier<F> uniqueField) {
        return isValid(uniqueField, () -> null);
    }

}
