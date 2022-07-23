package com.haisi.java.testfeatures.product.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryResponseDto {
    private Long id;
    private String name;
}
