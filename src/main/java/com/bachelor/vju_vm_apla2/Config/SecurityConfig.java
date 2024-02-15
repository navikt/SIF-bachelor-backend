package com.bachelor.vju_vm_apla2.Config;


import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import org.springframework.context.annotation.Configuration;
import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;

@Configuration
@EnableMockOAuth2Server(port = 8082)
@EnableJwtTokenValidation
public class SecurityConfig {
    //you find the port of the OUATH server by connecting with the port

}
