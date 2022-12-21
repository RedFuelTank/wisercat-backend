package com.wisercat.bestfriend.exception.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationError {
    @NonNull
    @JsonProperty(namespace = "code")
    private String name;
    @NonNull
    @JsonProperty(namespace = "field")
    private String field;
    @JsonProperty(namespace = "property")
    private List<String> arguments;
}
