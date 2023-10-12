package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.UserTokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthClient client;

    @Value("${kakao.token-url}")
    private String kakaoTokenUrl;

    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={여기에 REST API KEY를 입력해주세요}&redirect_uri=http://localhost:8080/callback
    public UserTokenDTO getKakaoAccesToken(String code){
        try {
            return client.getToken(new URI(kakaoTokenUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("토큰 발급 오류");
            return UserTokenDTO.fail();
        }
    }
}
