package com.wisercat.bestfriend.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TokenInfo {
    private String username;
    private List<String> roles;

    public TokenInfo(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public TokenInfo(String username, String roles) {
        this.username = username;
        this.roles = Arrays.stream(roles.split(", "))
                .toList();
    }

    public String getRolesAsString() {
        return String.join(", ", roles);
    }
}
