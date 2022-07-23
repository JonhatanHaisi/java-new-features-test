package com.haisi.java.testfeatures.product.dtos;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

import static com.haisi.java.testfeatures.utilities.validation.ValidationMessages.FIELD_LENGTH_MAX;
import static com.haisi.java.testfeatures.utilities.validation.ValidationMessages.FIELD_NOT_EMPTY;

@Getter
@Setter
@Builder
@Validated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {

    @NotEmpty(message= FIELD_NOT_EMPTY)
    @Size(max = 255, message = FIELD_LENGTH_MAX)
    private String name;

    @Size(max = 1000000000, message = FIELD_LENGTH_MAX)
    private String description;

    @NotEmpty
    private List<Long> categories;

}
