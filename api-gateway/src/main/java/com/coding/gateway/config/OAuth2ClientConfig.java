package com.coding.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OAuth2ClientConfig {

    @ConfigurationProperties(prefix = "spring.security.oauth2.client")
    @Bean
    public OAuth2ClientProperties oauth2ClientProperties() {
        return new OAuth2ClientProperties();
    }

    @Bean
    public ResourceOwnerPasswordAccessTokenProvider resourceOwnerPasswordAccessTokenProvider() {
        return new ResourceOwnerPasswordAccessTokenProvider();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
