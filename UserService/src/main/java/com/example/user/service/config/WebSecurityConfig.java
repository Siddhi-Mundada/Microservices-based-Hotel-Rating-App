package com.example.user.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity //traditional web security configuration, not flux like used in API Gateway
@EnableMethodSecurity(prePostEnabled = true) // Enables method-level security like @PreAuthorize and @PostAuthorize
public class WebSecurityConfig {

    @Bean //// Declares a SecurityFilterChain bean to customize security for HTTP requests
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security
                // Configure authorization rules for incoming HTTP requests
                .authorizeHttpRequests(auth -> auth
                        // All requests must be authenticated
                        .anyRequest().authenticated()
                )
                // Enables OAuth2 Resource Server support and sets up JWT decoding
                .oauth2ResourceServer(oauth2 -> oauth2.jwt()
                );

        // Build and return the configured SecurityFilterChain
        return security.build();
    }
}
