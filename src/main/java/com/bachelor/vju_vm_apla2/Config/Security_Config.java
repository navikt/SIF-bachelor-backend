package com.bachelor.vju_vm_apla2.Config;

/*
import no.nav.security.token.support.client.core.ClientProperties;
import no.nav.security.token.support.client.core.oauth2.OAuth2AccessTokenService;
import no.nav.security.token.support.client.spring.ClientConfigurationProperties;
import no.nav.security.token.support.client.spring.oauth2.ClientConfigurationPropertiesMatcher;
import no.nav.security.token.support.client.spring.oauth2.EnableOAuth2Client;

import no.nav.security.token.support.client.spring.oauth2.OAuth2ClientRequestInterceptor;
*/
import no.nav.security.token.support.core.configuration.MultiIssuerConfiguration;
import no.nav.security.token.support.core.configuration.ProxyAwareResourceRetriever;
import no.nav.security.token.support.core.context.TokenValidationContextHolder;
import no.nav.security.token.support.filter.JwtTokenValidationFilter;
import no.nav.security.token.support.jaxrs.JaxrsTokenValidationContextHolder;
import no.nav.security.token.support.jaxrs.servlet.JaxrsJwtTokenValidationFilter;
import no.nav.security.token.support.spring.MultiIssuerProperties;
import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//import javax.servlet.Filter;
import java.io.IOException;
import java.util.logging.LogRecord;


/* Essentially, the main annotation here for our prod project, is the @EnableJwtTokenValidation, which enables
   JWT validation on incoming requests. So any request that doesn't have a valid JWT token will be rejected */
//todo: might need to reconfigure this to work with a dockerfile, might need to change url only
//@EnableOAuth2Client(cacheEnabled = true) //enable ouath2client also makes sure that the tokens are vaildated through the webclient as well as
@Configuration
@EnableJwtTokenValidation
public class Security_Config {
 //due to the use of webclient, the client spring did not work properly, so it is commented out for now
/*@Bean
public RequestContextListener requestContextListener() {return new RequestContextListener();}*/
   /* @Bean
    public FilterRegistrationBean<RequestContextFilter> requestContextFilterRegistration() {
        FilterRegistrationBean<RequestContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestContextFilter());
        registration.setOrder(0); // you might need to set the order
        return registration;
    }*/
   /* public WebClientCustomizer customizer(OAuth2ClientRequestInterceptor reqInterceptor) {
        return webClientBuilder -> {
            ExchangeFilterFunction oauthFilter = ExchangeFilterFunction.ofRequestProcessor(
                    clientRequest -> {
                        ClientRequest filteredRequest = ClientRequest.from(clientRequest)
                                .headers(headers -> reqInterceptor.intercept(filteredRequest, null, null))
                                .build();
                        return Mono.just(filteredRequest);
                    }
            );
            webClientBuilder.filter(oauthFilter);
        };
    }

        @Bean
        public OAuth2ClientRequestInterceptor requestInterceptor(ClientConfigurationProperties properties, OAuth2AccessTokenService service, ClientConfigurationPropertiesMatcher matcher) {
            return new OAuth2ClientRequestInterceptor(properties, service, matcher);
        }

        @Bean
        public ClientConfigurationPropertiesMatcher configMatcher() {
            return new ClientConfigurationPropertiesMatcher() {};
        }

*/
 /*@Bean
    public Filter requestContextFilter() {
     return new Filter() {
         private static final Logger log = LoggerFactory.getLogger(RequestContextFilter.class);
         public void doFilter(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse, javax.servlet.FilterChain filterChain) throws IOException {
             try {
                 log.debug("inside doFilter");
                 ServletRequestAttributes attributes = new  ServletRequestAttributes((HttpServletRequest) servletRequest);
             } finally {
                 RequestContextHolder.resetRequestAttributes();
             }
         }
         public void init(javax.servlet.FilterConfig filterConfig) {

         }

         public void destroy(){

         }

     };
 }

  *//*  @Bean
    public FilterRegistrationBean<Filter> Filterregistation() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(requestContextFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestContextFilter");
        registration.setOrder(1);
        return registration;
    }*/
 /*  @Bean
   public ExchangeFilterFunction requestContextFilter() {
       return (clientRequest, next) -> {
           try {
               // Set up the request context
               ServletRequestAttributes attributes = new ServletRequestAttributes((HttpServletRequest) clientRequest);
               RequestContextHolder.setRequestAttributes(attributes);

               // Proceed with the filter chain
               return next.exchange(clientRequest);
           } finally {
               // Reset the request context after processing the request
               RequestContextHolder.resetRequestAttributes();
           }
       };
   }*/
 }



