package com.wisercat.bestfriend.exception.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationErrors {
    @Getter
    @JsonProperty(namespace = "errors")
    private List<ValidationError> errors = new ArrayList<>();

    public void addFieldError(FieldError fieldError) {
        List<String> args = Stream.of(fieldError.getArguments())
                .filter(arg -> !(arg instanceof DefaultMessageSourceResolvable))
                .map(String::valueOf)
                .collect(Collectors.toList());

        errors.add(new ValidationError(fieldError.getCode(), fieldError.getField(), args));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}