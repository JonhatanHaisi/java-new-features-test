package com.haisi.java.testfeatures.category.validation.name;

import com.haisi.java.testfeatures.category.dtos.CategoryCreateDto;
import com.haisi.java.testfeatures.category.dtos.CategoryUpdateDto;
import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import com.haisi.java.testfeatures.utilities.exceptions.ImplementationErrorException;
import com.haisi.java.testfeatures.utilities.validation.unique.UniqueFieldValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator
    extends UniqueFieldValidation<CategoryEntity, String, Long>
    implements ConstraintValidator<UniqueCategoryName, Object> {

    public UniqueCategoryNameValidator(CategoryRepository repository) {
        super(repository::findByName, CategoryEntity::getId);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof CategoryCreateDto dto) {
            return isValid(dto::getName);
        }

        if (value instanceof CategoryUpdateDto dto) {
            return isValid(dto::getName, dto::getId);
        }

        throw new ImplementationErrorException("Wrong dto type");
    }

}
