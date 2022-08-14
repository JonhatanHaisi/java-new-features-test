package com.haisi.java.testfeatures.category.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haisi.java.testfeatures.category.validation.name.UniqueCategoryName;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.haisi.java.testfeatures.utilities.validation.ValidationMessages.FIELD_LENGTH_MAX;
import static com.haisi.java.testfeatures.utilities.validation.ValidationMessages.FIELD_NOT_EMPTY;

@Getter
@Setter
@Builder
@Validated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@UniqueCategoryName
public class CategoryUpdateDto {

    @NotNull
    @JsonIgnore
    private Long id;

    @NotEmpty(message= FIELD_NOT_EMPTY)
    @Size(max = 255, message = FIELD_LENGTH_MAX)
    private String name;

}
