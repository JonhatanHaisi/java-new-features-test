package com.haisi.java.testfeatures.category.model;

import com.haisi.java.testfeatures.data.entity.CategoryEntity;
import com.haisi.java.testfeatures.data.metamodel.CategoryEntity_;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import com.haisi.java.testfeatures.utilities.abstractions.PageableSearch;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import com.haisi.java.testfeatures.utilities.qualifiers.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.empty;

@Category
@Component
public class CategoryPageableSearch extends PageableSearch<CategoryPageableSearch.CategoryFilter, CategoryEntity> {


    public CategoryPageableSearch(CategoryRepository repository, Mapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Specification<CategoryEntity> createFilter(CategoryFilter filter) {
        return (root, query, builder) ->
            filter.getName()
                    .map(name -> builder.like(root.get(CategoryEntity_.name), name+"%"))
                    .orElse(null);
    }

    @Override
    protected Optional<Sort> createSort(CategoryFilter filter) {
        return empty();
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class CategoryFilter {
        private final Optional<String> name;
    }
}
