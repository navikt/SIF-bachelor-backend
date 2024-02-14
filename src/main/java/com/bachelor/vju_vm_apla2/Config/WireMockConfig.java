package com.bachelor.vju_vm_apla2.Config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
public class WireMockConfig {

    //setter opp wiremock server og enabler logging
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return new WireMockServer(options()
                .port(8081)
                .usingFilesUnderClasspath(".")
                .notifier(new ConsoleNotifier(true)));
    }

}