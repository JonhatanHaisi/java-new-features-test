package com.haisi.java.testfeatures.utilities.web.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder(access = PRIVATE)
@RequiredArgsConstructor(access = PRIVATE)
public class Page<T> {

    public static <R> Page<R> of(List<R> content, Pageable pageable, Long totalElements) {
        return Page.<R>builder()
            .content(content)
            .totalElements(totalElements)
            .size(pageable.getPageSize())
            .page(pageable.getPageNumber())
            .build();
    }

    private final List<T> content;
    private final Integer size;
    private final Integer page;
    private final Long totalElements;

    public Integer getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) getSize());
    }

}
