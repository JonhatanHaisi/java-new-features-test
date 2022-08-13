package com.haisi.java.testfeatures.category.validation.name;

import com.haisi.java.testfeatures.utilities.validation.types.IdentifiedDto;
import com.haisi.java.testfeatures.utilities.validation.types.NamedDto;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, NamedDto> {

    private final CategoryRepository repository;

    @Override
    public boolean isValid(NamedDto value, ConstraintValidatorContext context) {
        if (isNull(value.getName())) return true;

        var id = getId(value);
        return repository.findByName(value.getName())
                .filter(it -> isNull(id) || !Objects.equals(id, it.getId()))
                .isEmpty();
    }

    private Long getId(NamedDto value) {
        return Optional.of(value)
            .filter(IdentifiedDto.class::isInstance)
            .map(IdentifiedDto.class::cast)
            .map(IdentifiedDto::getId)
            .orElse(null);
    }
}
