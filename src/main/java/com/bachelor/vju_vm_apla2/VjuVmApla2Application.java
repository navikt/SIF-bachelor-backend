package com.bachelor.vju_vm_apla2;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wiremock.org.apache.hc.client5.http.impl.Wire;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.autoconfigure.AutoConfigurationPackages.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


@SpringBootApplication
public class VjuVmApla2Application {

    //static WireMockServer server = new WireMockServer(8081);
    public static void main(String[] args) {

        SpringApplication.run(VjuVmApla2Application.class, args);
    }

}
