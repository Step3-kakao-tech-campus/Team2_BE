package com.example.team2_be.core.config;

import feign.Client;
import feign.Request;
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

    @Bean
    public Request.Options options() {
        return new Request.Options(/* connectTimeoutMillis */ 60000, /* readTimeoutMillis */ 300000);
    }
}
