package com.haisi.java.testfeatures.category.model;

import com.haisi.java.testfeatures.category.dtos.CategoryFilter;
import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.metamodel.CategoryEntity_;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import com.haisi.java.testfeatures.utilities.abstractions.PageableSearch;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import com.haisi.java.testfeatures.utilities.qualifiers.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.empty;

@Category
@Component
public class CategoryPageableSearch extends PageableSearch<CategoryFilter, CategoryEntity> {


    public CategoryPageableSearch(CategoryRepository repository, Mapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Specification<CategoryEntity> createFilter(CategoryFilter filter) {
        return (root, query, builder) ->
            filter.getName()
                    .map(name -> builder.like(root.get(CategoryEntity_.NAME), name+"%"))
                    .orElse(null);
    }

    @Override
    protected Optional<Sort> createSort(CategoryFilter filter) {
        return empty();
    }


}
