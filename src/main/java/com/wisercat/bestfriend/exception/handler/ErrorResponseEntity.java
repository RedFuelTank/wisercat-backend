package com.wisercat.bestfriend.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponseEntity {
    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "message")
    private String message;
}
