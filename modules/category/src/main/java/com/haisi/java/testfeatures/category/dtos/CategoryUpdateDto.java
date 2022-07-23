package com.haisi.java.testfeatures.category.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.haisi.java.testfeatures.utilities.validation.ValidationMessages.*;

@Getter
@Setter
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryUpdateDto {

    @NotNull
    @JsonIgnore
    private Long id;

    @NotEmpty(message= FIELD_NOT_EMPTY)
    @Size(max = 255, message = FIELD_LENGTH_MAX)
    private String name;

}
