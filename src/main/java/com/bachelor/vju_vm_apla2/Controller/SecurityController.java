package com.bachelor.vju_vm_apla2.Controller;

import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import no.nav.security.mock.oauth2.MockOAuth2Server;
import no.nav.security.mock.oauth2.token.DefaultOAuth2TokenCallback;
import no.nav.security.token.support.core.api.Unprotected;
import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import okhttp3.HttpUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class SecurityController {
    //////////////////////////////////////////////////// Security ///////////////////////////////////////////////////////////////////////
    private final MockOAuth2Server oAuth2Server = new MockOAuth2Server();
    @PostConstruct
    public void startOuath(){
        oAuth2Server.start(8082); //starts the oauth server at a random port
    }

    @PreDestroy
    public  void stopOuath(){
        oAuth2Server.shutdown(); //makes sure that the server has stopped
    }
    @Unprotected
    @GetMapping("/sec/getToken")
    public SignedJWT getTokenFromServer() {

        String issuerId = "jegerenbanan";
        String claims = "Boss";
        DefaultOAuth2TokenCallback callback = new DefaultOAuth2TokenCallback(issuerId, claims);
        oAuth2Server.enqueueCallback(callback); //Decides the standard callback for the OAUTH2.0 server
        HashMap<String, Integer> hm = new HashMap<>();
        SignedJWT t = oAuth2Server.anyToken(HttpUrl.get("http://localhost:8082/issuer1"),  hm); //creates a token, this is to test the tokenprovider, from mock ouath server
        System.out.println(t.serialize());
        return t;
    }
}
