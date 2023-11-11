package com.example.team2_be.core.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google")
@Data
public class GoogleAuthProperties {
    private String tokenUrl;
    private String userApiUrl;
    private String redirectUrl;
    private String clientId;
    private String clientSecret;
}
