package com.bachelor.vju_vm_apla2.Config;
import no.nav.security.token.support.client.spring.oauth2.EnableOAuth2Client;
import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.springframework.context.annotation.Configuration;


/* Essentially, the main annotation here for our prod project, is the @EnableJwtTokenValidation, which enables
   JWT validation on incoming requests. So any request that doesn't have a valid JWT token will be rejected */
//todo: might need to reconfigure this to work with a dockerfile, might need to change url only
@EnableOAuth2Client(cacheEnabled = true)
//enable ouath2client also makes sure that the tokens are vaildated through the webclient as well as
@Configuration
@EnableJwtTokenValidation

public class SecurityConfig {

    //according to docs - it is auto configurable in spring boot sooo...
/*@Bean
public WebClientCustomizer customizer(OAuth2ClientRequestInterceptor reqInterceptor) {
    return client -> client.(reqInterceptor);
}*/
/*@Bean
    public OAuth2ClientRequestInterceptor oAuth2ClientRequestInterceptor(ClientConfigurationProperties properties, OAuth2AccessTokenService service, ClientConfigurationPropertiesMatcher matcher) {
    return new OAuth2ClientRequestInterceptor(properties, service, matcher);
}*/
/*    @Bean
    public MultiIssuerProperties multiIssuerConfiguration() {
        return new MultiIssuerProperties();
    }
    @Bean
    public FilterRegistrationBean<JwtTokenValidationFilter> oidcTokenValidationFilterBean(MultiIssuerConfiguration config) {
        return new FilterRegistrationBean<>(new JaxrsJwtTokenValidationFilter(config));
    }*/
}
