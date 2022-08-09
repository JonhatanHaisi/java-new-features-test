package com.haisi.java.testfeatures.category.validation.name;

import com.haisi.java.testfeatures.utilities.validation.types.IdentifiedDto;
import com.haisi.java.testfeatures.utilities.validation.types.NamedDto;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, NamedDto> {

    private final CategoryRepository repository;

    @Override
    public boolean isValid(NamedDto value, ConstraintValidatorContext context) {
        return getId(value)
            .map(id ->
                repository.findByName(value.getName())
                    .filter(it -> !Objects.equals(it.getId(), id)).isEmpty())
                    .orElseGet(() -> repository.findByName(value.getName()).isEmpty()
            );

    }

    private Optional<Long> getId(NamedDto value) {
        return Optional.of(value)
            .filter(IdentifiedDto.class::isInstance)
            .map(IdentifiedDto.class::cast)
            .map(IdentifiedDto::getId);
    }
}
