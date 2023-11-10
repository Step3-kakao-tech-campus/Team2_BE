package com.example.team2_be.auth.dto.kakao;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoAccountDTO{
    private ProfileDTO profile;
    private String email;
}