package com.example.team2_be.kakao;

import com.example.team2_be.auth.security.JwtTokenProvider;
import com.example.team2_be.kakao.DTO.KakaoAccount;
import com.example.team2_be.kakao.DTO.KakaoToken;
import com.example.team2_be.kakao.util.KakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
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

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    public String login(String code) {
        KakaoToken kakaoToken = getToken(code);
        KakaoAccount kakaoAccount = null;

        try {
            kakaoAccount = client.getInfo(new URI(kakaoUserApiUrl), kakaoToken.getTokenType() + " " + kakaoToken.getAccessToken()).getKakaoAccount();
        } catch (Exception e) {
            log.error("유저 정보 확인 오류");
        }

        return JwtTokenProvider.create(userService.dto(kakaoAccount)); //kakao에서 받아온 kakaoAccount를 user dto로 변경하는 로직 필요
    }


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
