package com.example.team2_be.core.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kakao")
@Data
public class KakaoAuthProperties {
    private String tokenUrl;
    private String userApiUrl;
    private String restApiKey;
    private String clientSecret;
    private String redirectUrl;
}
