package com.example.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity //coz spring web flux is used in pom.xml not @EnanbleWebSecurity
public class SecurityConfig {
//    For Customizing Security filter chain
//    Make it a @Configuration class by adding this annotation above class-name so Spring can search for configurations here
//    Dont want to go with default security configuration and want to define it here so use = @EnableWebFluxSecurity
//    Define bean here (using annotation @Configuration so we can define @Bean here)which returns SecurityWebFilterChain
//    In that add all the security u wish to implement in chain format such as jwt, authentication, authorization,

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

//      It secures all incoming requests (anyExchange().authenticated())
//      It enables JWT-based authentication, so clients must send a valid Okta access token

        return http
                // Step 1: Authorize all exchanges - HTTP requests
                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                // Require authentication for **all** incoming requests

                // Step 2: Enable OAuth2 Login
                .oauth2Login(Customizer.withDefaults())

                // Step 3: This tells Spring Security to expect a JWT in the Authorization header (Bearer token)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))

                // Step 4: Build and return the SecurityWebFilterChain bean
                .build();
    }
}
