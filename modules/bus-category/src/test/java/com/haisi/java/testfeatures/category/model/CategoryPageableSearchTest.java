package com.haisi.java.testfeatures.category.model;

import com.haisi.java.testfeatures.category.dtos.CategoryFilter;
import com.haisi.java.testfeatures.data.metamodel.CategoryEntity_;
import com.haisi.java.testfeatures.data.repository.CategoryRepository;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryPageableSearchTest {

    @InjectMocks
    private CategoryPageableSearch pageableSearch;

    @Mock
    private CategoryRepository repository;

    @Mock
    private Mapper mapper;

    @Test
    @DisplayName("Create a filter to the pageable request")
    void createFilter() {
        var result = pageableSearch.createFilter(
            CategoryFilter.builder()
                .name(Optional.empty())
                .build()
        );

        assertNull(result.toPredicate(null, null, null), "Filter should be null");
    }

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Create a filter to the pageable request - filter by name")
    void createFilter_name() {
        var result = pageableSearch.createFilter(
                CategoryFilter.builder()
                        .name(Optional.of("name"))
                        .build()
        );

        var root = mock(Root.class);
        var query = mock(CriteriaQuery.class);
        var builder = mock(CriteriaBuilder.class);
        var path = mock(Path.class);
        var predicate = mock(Predicate.class);

        when(root.get(CategoryEntity_.NAME)).thenReturn(path);
        when(builder.like(path, "name%")).thenReturn(predicate);

        var filter = result.toPredicate(root, query, builder);

        assertSame(predicate, filter, "Wrong generated filter");
        verify(root).get(CategoryEntity_.NAME);
        verify(builder).like(path, "name%");
    }

    @Test
    @DisplayName("Create a sort object to the pageable  request")
    void createSort() {
        var result = pageableSearch.createSort(CategoryFilter.builder().build());
        assertTrue(result.isEmpty(), "Wrong sort result");
    }

}
