package com.haisi.java.testfeatures.utilities.abstractions.impl;

import com.haisi.java.testfeatures.utilities.abstractions.PageableSearch;
import com.haisi.java.testfeatures.utilities.mapping.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PageableSearchTest {

    @InjectMocks
    private PageableSearchStub pageableSearch;

    @Mock
    private JpaSpecificationExecutor<Object> repository;

    @Mock
    private Mapper mapper;

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Find all data paged")
    void findAll() {
        var pageContent = singletonList(new Object());
        var page = mock(Page.class);
        var mappedContent = singletonList("mapped");

        when(repository.findAll(eq(null), any(Pageable.class))).thenReturn(page);
        when(page.getContent()).thenReturn(pageContent);
        when(page.getTotalElements()).thenReturn(2L);
        when(mapper.mapAll(pageContent, String.class)).thenReturn(mappedContent);

        var result = pageableSearch.findAll(null, 0, 1, String.class);

        assertEquals(2L, result.getTotalElements(), "Wrong total elements");
        assertEquals(mappedContent, result.getContent(), "Wrong result");
        assertEquals(0, result.getPage(), "Wrong page");
        assertEquals(1, result.getSize(), "Wrong page size");
        assertEquals(2, result.getTotalPages(), "Wrong total pages");
    }

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Find all data paged with sort")
    void findAll_nameFilter() {
        var pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        var pageContent = singletonList(new Object());
        var page = mock(Page.class);
        var mappedContent = singletonList("mapped");

        when(repository.findAll(eq(null), any(Pageable.class))).thenReturn(page);
        when(page.getContent()).thenReturn(pageContent);
        when(page.getTotalElements()).thenReturn(2L);
        when(mapper.mapAll(pageContent, String.class)).thenReturn(mappedContent);

        var result = pageableSearch.findAll("sort", 0, 1, String.class);

        assertEquals(2L, result.getTotalElements(), "Wrong total elements");
        assertEquals(mappedContent, result.getContent(), "Wrong result");
        assertEquals(0, result.getPage(), "Wrong page");
        assertEquals(1, result.getSize(), "Wrong page size");
        assertEquals(2, result.getTotalPages(), "Wrong total pages");

        verify(repository).findAll(eq(null), pageableArgumentCaptor.capture());
        var pageable = pageableArgumentCaptor.getValue();
        var sort = pageable.getSort().get().map(Sort.Order::getProperty).findFirst().orElse(null);
        assertEquals("sort", sort, "Wrong sort");
    }


    public static class PageableSearchStub extends PageableSearch<String, Object> {

        public PageableSearchStub(JpaSpecificationExecutor<Object> repository, Mapper mapper) {
            super(repository, mapper);
        }

        @Override
        protected Specification<Object> createFilter(String filter) {
            return null;
        }

        @Override
        protected Optional<Sort> createSort(String filter) {
            if (isNull(filter)) return Optional.empty();
            return Optional.of(Sort.by(filter));
        }
    }

}
