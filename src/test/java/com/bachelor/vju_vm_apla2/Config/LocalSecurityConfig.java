package com.bachelor.vju_vm_apla2.Config;


//import no.nav.security.token.support.client.spring.oauth2.EnableOAuth2Client;
import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Profile("local")
@Configuration
//@EnableOAuth2Client(cacheEnabled = true) //enable ouath2client also makes sure that the tokens are vaildated through the webclient as well as
@EnableMockOAuth2Server
public class LocalSecurityConfig {
}
