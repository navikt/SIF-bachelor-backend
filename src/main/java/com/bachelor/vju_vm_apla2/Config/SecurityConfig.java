package com.bachelor.vju_vm_apla2.Config;

import no.nav.security.token.support.client.spring.oauth2.EnableOAuth2Client;
import no.nav.security.token.support.core.configuration.MultiIssuerConfiguration;
import no.nav.security.token.support.core.configuration.ProxyAwareResourceRetriever;
import no.nav.security.token.support.core.context.TokenValidationContextHolder;
import no.nav.security.token.support.filter.JwtTokenValidationFilter;
import no.nav.security.token.support.jaxrs.JaxrsTokenValidationContextHolder;
import no.nav.security.token.support.jaxrs.servlet.JaxrsJwtTokenValidationFilter;
import no.nav.security.token.support.spring.MultiIssuerProperties;
import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.request.RequestContextListener;

/* Essentially, the main annotation here for our prod project, is the @EnableJwtTokenValidation, which enables
   JWT validation on incoming requests. So any request that doesn't have a valid JWT token will be rejected */
//todo: might need to reconfigure this to work with a dockerfile, might need to change url only
//@EnableOAuth2Client(cacheEnabled = true)
@Configuration
@EnableJwtTokenValidation
//based on : https://github.com/navikt/sendsoknad-boot/blob/04feb113ff98c7916d646aa0af5f4f57aac2a271/sendsoknad-web/src/main/java/no/nav/sbl/dialogarena/config/TokenSupportConfig.java#L40
public class SecurityConfig {
@Bean
    public MultiIssuerProperties mulitIssuerProperties() {
    return new MultiIssuerProperties();
}
/*
@Bean
    public FilterRegistrationBean<JwtTokenValidationFilter> oidcTokenValidationFilterBean(MultiIssuerConfiguration config){
    return new FilterRegistrationBean<JwtTokenValidationFilter>(new JaxrsJwtTokenValidationFilter(config));
}
@Bean
    public MultiIssuerConfiguration multiIssuerConfiguration(MultiIssuerProperties issuerProperties, ProxyAwareResourceRetriever resourceRetriever){
    return  new MultiIssuerConfiguration(issuerProperties.getIssuer(), resourceRetriever);
}
*/

@Bean
    public ProxyAwareResourceRetriever oidcResourceReteiver(){
    return new ProxyAwareResourceRetriever();
}
/*@Bean
    public RequestContextListener requestContextListener(){
    return new RequestContextListener();
}
*/
@Bean
    public TokenValidationContextHolder jaxrsContextHolder() {
    return JaxrsTokenValidationContextHolder.getHolder();
}



}
