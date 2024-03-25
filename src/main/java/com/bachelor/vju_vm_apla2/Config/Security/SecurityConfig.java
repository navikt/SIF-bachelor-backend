package com.bachelor.vju_vm_apla2.Config.Security;

import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/* Essentially, the main annotation here for our prod project, is the @EnableJwtTokenValidation, which enables
   JWT validation on incoming requests. So any request that doesn't have a valid JWT token will be rejected */
@Configuration
@EnableJwtTokenValidation
public class SecurityConfig {

}
