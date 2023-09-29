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

    @Value("${kakao.token-url}")
    private String kakaoTokenUrl;

    @Value("&{kakao.auth-url}")
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

        //email로 user 확인 후 없으면 DB에 생성 하는 로직 필요, KakaoAccount 내의 정보 사용
        //있으면 user 정보를 가져오는 로직 필요

        return JwtTokenProvider.create(user);
    }


    public KakaoToken getToken(String code) {
        try {
            return client.getToken(new URI(kakaoTokenUrl), restapiKey, redirectUrl, code, "authorization_code");
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
