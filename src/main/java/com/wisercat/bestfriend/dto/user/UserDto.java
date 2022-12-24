package com.wisercat.bestfriend.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @JsonProperty(namespace = "username")
    @Size(min = 1)
    @NonNull
    private String username;
}
