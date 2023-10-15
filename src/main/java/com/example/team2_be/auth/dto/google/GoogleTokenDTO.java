package com.example.team2_be.auth.dto.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleTokenDTO {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private String idToken;

    public static GoogleTokenDTO fail(){return new GoogleTokenDTO(null, null);}

    private GoogleTokenDTO(final String accessToken, final String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "GoogleTokenDTO{" +
                "tokenType='" + tokenType + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", idToken='" + idToken + '\'' +
                '}';
    }
}