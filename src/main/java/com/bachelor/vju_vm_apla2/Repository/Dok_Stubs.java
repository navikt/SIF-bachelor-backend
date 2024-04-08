package com.bachelor.vju_vm_apla2.Repository;

import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Dok_Stubs {

    static private WireMockServer wireMockServer;

    @Autowired
    public Dok_Stubs(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }

    @PostConstruct
    public void configureStubs() {
        wireMockServer.start();
        // todo :  adding edge cases for calls that needs to be authenticated
        // todo : Add errorcode handling


    }
    @PreDestroy
    public void stopWireMockServer() {
        wireMockServer.stop();
    }



}
