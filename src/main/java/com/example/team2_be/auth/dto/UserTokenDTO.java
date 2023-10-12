package com.example.team2_be.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserTokenDTO {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private Long refreshTokenExpiresIn;
    
    public static UserTokenDTO fail(){
        return new UserTokenDTO(null, null);
    }
    
    private UserTokenDTO(final String accessToken, final String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
