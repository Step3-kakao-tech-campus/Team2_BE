package com.example.team2_be.auth.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoTokenDTO {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private Long refreshTokenExpiresIn;
    
    public static KakaoTokenDTO fail(){
        return new KakaoTokenDTO(null, null);
    }
    
    private KakaoTokenDTO(final String accessToken, final String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
