package com.example.team2_be.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient client;

    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    public KakaoToken getToken(String code) {
        try {
            return client.getToken(new URI(kakaoAuthUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            return KakaoToken.fail();
        }
    }

    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={여기에 REST API KEY를 입력해주세요}&redirect_uri=http://localhost:8080/callback
    public String getAuthCodeUrl() {
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+restapiKey+"&redirect_uri="+redirectUrl;
        return url;
    }
}
