package com.example.team2_be.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GoogleAccessTokenRequestDTO {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;
    private String grantType;
}
