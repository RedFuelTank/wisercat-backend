package com.wisercat.bestfriend.config.security;

import com.wisercat.bestfriend.config.security.handler.ApiAccessDeniedHandler;
import com.wisercat.bestfriend.config.security.handler.ApiEntryPoint;
import com.wisercat.bestfriend.config.security.jwt.JwtAuthenticationFilter;
import com.wisercat.bestfriend.config.security.jwt.JwtAuthorizationFilter;
import com.wisercat.bestfriend.config.security.jwt.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserConfig userConfig;
    private final JwtConfig jwtConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(new ApiEntryPoint())
                .accessDeniedHandler(new ApiAccessDeniedHandler());

        http.authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/**").authenticated();

        http.apply(new FilterConfigurer());

        return http.build();
    }

    class FilterConfigurer extends AbstractHttpConfigurer<FilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager manager = http.getSharedObject(AuthenticationManager.class);
            http.addFilterBefore(
                    new JwtAuthenticationFilter(manager, "/login", jwtConfig),
                    UsernamePasswordAuthenticationFilter.class
            );
            http.addFilterBefore(
                    new JwtAuthorizationFilter(jwtConfig.getKey()),
                    AuthorizationFilter.class);

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }


    @Bean
    public UserDetailsService getUserDetails(AuthenticationManagerBuilder auth) {
        UserDetails user = User.builder()
                .username(userConfig.getUserName())
                .password(getPasswordEncoder().encode(userConfig.getUserPassword()))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username(userConfig.getAdminName())
                .password(getPasswordEncoder().encode(userConfig.getAdminPassword()))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }



}
