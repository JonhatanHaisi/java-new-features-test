package com.haisi.java.testfeatures.utilities.web.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    @JsonInclude(NON_EMPTY)
    private String field;
    private String message;

}
