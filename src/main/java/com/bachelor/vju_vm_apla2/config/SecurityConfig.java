package com.bachelor.vju_vm_apla2.config;


import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.springframework.context.annotation.Configuration;
import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;

@Configuration
@EnableMockOAuth2Server
//@EnableJwtTokenValidation
public class SecurityConfig {
}
