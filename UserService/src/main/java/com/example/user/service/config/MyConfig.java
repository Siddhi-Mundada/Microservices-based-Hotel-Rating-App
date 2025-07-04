package com.example.user.service.config;

import com.example.user.service.config.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration //coz declaring Bean
public class MyConfig {

    @Autowired
    private  ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private  OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;


    @Bean
    @LoadBalanced // This annotation enables load balancing...
    // means we can replace the URL host:port with the service name
    //http://localhost:8083/ratings/user/{userId} => http://RATING-SERVICE/ratings/user/{userId}
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors=new ArrayList<>();

        interceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository, oAuth2AuthorizedClientRepository )));

        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    //declare the bean of OAuth2AuthorizedClient manager used in
    // Feign Client & Rest Template Interceptor to get the token
    @Bean
    public OAuth2AuthorizedClientManager manager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
    ) {
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
        return defaultOAuth2AuthorizedClientManager;


    }
}

