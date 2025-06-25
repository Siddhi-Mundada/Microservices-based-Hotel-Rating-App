package com.example.user.service.config.interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    // this class will add Header with Bearer token in request and then send that request forward

    //bean in MyConfig class -This manager handles OAuth2 authorization to get access tokens
    //Constructor Injection
    private OAuth2AuthorizedClientManager manager;

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    private Logger logger= LoggerFactory.getLogger(RestTemplateInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token = manager.authorize
                (OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").
                        principal("internal").build()).getAccessToken().getTokenValue();

        logger.info("Rest Template interceptor: Token :  {} ",token);

        request.getHeaders().add("Authorization","Bearer "+token);
        return execution.execute(request,body);
    }
}
