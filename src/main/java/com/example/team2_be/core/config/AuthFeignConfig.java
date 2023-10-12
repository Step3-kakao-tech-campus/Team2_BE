package com.example.team2_be.core.config;

import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthFeignConfig {
    @Bean
    public Client feignClient() {
            return new Client.Default(null, null);
        }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

