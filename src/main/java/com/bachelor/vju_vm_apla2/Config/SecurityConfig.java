package com.bachelor.vju_vm_apla2.Config;


import no.nav.security.token.support.client.core.oauth2.OAuth2AccessTokenService;
import no.nav.security.token.support.client.spring.ClientConfigurationProperties;
import no.nav.security.token.support.client.spring.oauth2.ClientConfigurationPropertiesMatcher;
import no.nav.security.token.support.client.spring.oauth2.EnableOAuth2Client;

import no.nav.security.token.support.client.spring.oauth2.OAuth2ClientRequestInterceptor;
import no.nav.security.token.support.spring.MultiIssuerProperties;
import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


import javax.servlet.FilterRegistration;
import java.net.URI;
import java.util.Map;

/* Essentially, the main annotation here for our prod project, is the @EnableJwtTokenValidation, which enables
   JWT validation on incoming requests. So any request that doesn't have a valid JWT token will be rejected */
//todo: might need to reconfigure this to work with a dockerfile, might need to change url only
@EnableOAuth2Client(cacheEnabled = true) //enable ouath2client also makes sure that the tokens are vaildated through the webclient as well as
@Configuration
@EnableJwtTokenValidation
public class SecurityConfig {
/*
    @Bean
    public OAuth2ClientRequestInterceptor oAuth2ClientRequestInterceptor(ClientConfigurationProperties properties, OAuth2AccessTokenService service, ClientConfigurationPropertiesMatcher matcher) {
        return new OAuth2ClientRequestInterceptor(properties, service, matcher);
    }
    //according to docs - it is auto configurable in spring boot sooo...
    @Bean
    public WebClientCustomizer customizer(OAuth2ClientRequestInterceptor requestInterceptor) {
       return new WebClientCustomizer() {
           @Override
           public void customize(WebClient.Builder webClientBuilder) {
               webClientBuilder.filter(requestInterceptor);
           }
       }; 
    }

    @Bean
    public Object configMatcher() {
        return new ClientConfigurationPropertiesMatcher() {};
    }

    @Bean
    public MultiIssuerProperties multiIssuerConfiguration() {
        return new MultiIssuerProperties();
    }
*/

}
