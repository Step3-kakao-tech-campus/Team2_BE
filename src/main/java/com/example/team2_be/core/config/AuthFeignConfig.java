package com.example.team2_be.core.config;

import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HttpsURLConnection;

@Configuration
public class AuthFeignConfig {
    @Bean
    public Client feignClient() {
        return new Client.Default(HttpsURLConnection.getDefaultSSLSocketFactory(),
                HttpsURLConnection.getDefaultHostnameVerifier());
    }
}
