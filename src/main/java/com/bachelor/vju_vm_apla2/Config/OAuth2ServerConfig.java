package com.bachelor.vju_vm_apla2.Config;
/*
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import no.nav.security.mock.oauth2.MockOAuth2Server;
import no.nav.security.mock.oauth2.token.DefaultOAuth2TokenCallback;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OAuth2ServerConfig {

    private MockOAuth2Server server;

    @PostConstruct
    public void startServer(){
        server = new MockOAuth2Server();
        String issuerId = "issuer1";
        String claims = "testUser";
        DefaultOAuth2TokenCallback callback = new DefaultOAuth2TokenCallback(issuerId, claims);
        server.enqueueCallback(callback);
        server.start(8082);
    }

    @PreDestroy
    public void stopServer(){
        if(server!=null){
            server.shutdown();
        }
    }
}*/