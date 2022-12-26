package com.wisercat.bestfriend.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.users")
@Getter
@Setter
public class UserConfig {
    private String userName;
    private String userPassword;
    private String adminName;
    private String adminPassword;

}
