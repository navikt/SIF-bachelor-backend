package com.bachelor.vju_vm_apla2.Config;

import freemarker.core.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig {

    @Value("${FRONTEND.COMBINED}")
    private String CROS;

    @Value("${OUATH2.COMBINED}")
    private String OAUTH2Server;
    @Bean
    public WebMvcConfigurer corsconf() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**").allowedOrigins(CROS).allowedMethods("*");
                reg.addMapping("/**").allowedOrigins(OAUTH2Server).allowedMethods("*");
            }

    };
    }
}
