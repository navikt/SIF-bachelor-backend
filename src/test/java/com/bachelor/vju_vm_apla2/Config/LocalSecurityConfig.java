package com.bachelor.vju_vm_apla2.Config;


import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
//@EnableMockOAuth2Server
public class LocalSecurityConfig {
}
