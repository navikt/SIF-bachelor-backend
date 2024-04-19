package com.bachelor.vju_vm_apla2.Config;

import no.nav.security.token.support.client.core.ClientProperties;
import no.nav.security.token.support.client.core.oauth2.OAuth2AccessTokenService;
import no.nav.security.token.support.client.spring.ClientConfigurationProperties;
import no.nav.security.token.support.client.spring.oauth2.ClientConfigurationPropertiesMatcher;
import no.nav.security.token.support.client.spring.oauth2.EnableOAuth2Client;

import no.nav.security.token.support.client.spring.oauth2.OAuth2ClientRequestInterceptor;
import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


import java.net.URI;
import java.util.Map;

/* Essentially, the main annotation here for our prod project, is the @EnableJwtTokenValidation, which enables
   JWT validation on incoming requests. So any request that doesn't have a valid JWT token will be rejected */
//todo: might need to reconfigure this to work with a dockerfile, might need to change url only
@EnableOAuth2Client(cacheEnabled = true)
@Configuration
@EnableJwtTokenValidation

public class SecurityConfig {
/*
//according to docs - it is auto configurable in spring boot sooo...
@Bean
public RestClientCustomizer customizer(OAuth2ClientRequestInterceptor reqInterceptor) {
    return client -> client.requestInterceptor(reqInterceptor);
}
@Bean
    public OAuth2ClientRequestInterceptor oAuth2ClientRequestInterceptor(ClientConfigurationProperties properties, OAuth2AccessTokenService service, ClientConfigurationPropertiesMatcher matcher) {
    return new OAuth2ClientRequestInterceptor(properties, service, matcher);
}*/

}
