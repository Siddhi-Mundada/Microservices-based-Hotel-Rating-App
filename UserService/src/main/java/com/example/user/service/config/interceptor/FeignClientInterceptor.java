package com.example.user.service.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component //to make it available for Autowire
public class FeignClientInterceptor implements RequestInterceptor {

    // this class will add Header with Bearer token in request and then send that request forward

    //bean in MyConfig class
    @Autowired
    private OAuth2AuthorizedClientManager manager;
    // This manager handles OAuth2 authorization to get access tokens (auto-configured by Spring Security)

    @Override
    public void apply(RequestTemplate requestTemplate) {

        // Build an OAuth2 authorization request using the client registration ID
        // "my-internal-client" is the ID in application.properties under spring.security.oauth2.client.registration

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("my-internal-client")
                .principal("internal") // Principal name (can be anything when using client_credentials flow)
                .build();

        // Get the authorized client and access token from the manager
        String token = manager.authorize(authorizeRequest).getAccessToken().getTokenValue();

        // Add the access token to the request headers so that the downstream microservice(Rating & Hotel) receives it
        requestTemplate.header("Authorization", "Bearer " + token);
    }
}
