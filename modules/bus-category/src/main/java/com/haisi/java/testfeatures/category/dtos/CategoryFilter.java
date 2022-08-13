package com.haisi.java.testfeatures.category.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@RequiredArgsConstructor
public class CategoryFilter {

    private final Optional<String> name;

}
