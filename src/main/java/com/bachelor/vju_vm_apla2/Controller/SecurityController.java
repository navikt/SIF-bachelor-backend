package com.bachelor.vju_vm_apla2.Controller;

import no.nav.security.token.support.core.api.Protected;
import no.nav.security.token.support.core.api.Unprotected;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Protected
@RestController
public class SecurityController {

    @Value("${mock-oauth2-server.token-url}")
    private String oauth2Url;

    @Value("${mock-oauth2-server.clientId}")
    private String clientId;

    @Value("${mock-oauth2-server.clientSecret}")
    private String clientSecret;

    @Unprotected
    @GetMapping("/login")
    public ResponseEntity<String> login(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);

        try{
            return new RestTemplate().exchange(oauth2Url, HttpMethod.POST, new HttpEntity<>(requestBody, headers), String.class);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
