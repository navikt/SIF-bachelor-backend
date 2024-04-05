package com.bachelor.vju_vm_apla2.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig {
    //Frontend URL
    @Value("${FRONTEND.COMBINED}")
    private String CROS;
    //ouath2 url
   @Value("${mock-oauth2-server.combined}")
   private String OAUTH2Server;
   //wiremock url
   @Value("${db.combined}")
   private String db;
    @Bean
    public WebMvcConfigurer corsconf() {
    //all the different urls that are allowed to talk to the backend
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**").allowedOrigins(CROS, OAUTH2Server, db).allowedMethods("*").allowedHeaders("*");

            }

    };
    }
}
