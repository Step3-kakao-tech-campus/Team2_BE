package com.example.team2_be.auth.dto.kakao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoAccessTokenRequestDTO{
    private String clientId;
    private String redirectUri;
    private String code;
    private String grantType;
}
