package com.wisercat.bestfriend.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @JsonProperty(namespace = "username")
    @Size(min = 1)
    @NotNull
    private String username;
}
