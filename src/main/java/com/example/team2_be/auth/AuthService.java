package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.*;
import com.example.team2_be.core.security.JwtTokenProvider;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthClient client;
    private final GoogleAuthUserClient googleAuthUserClient;
    private final GoogleAuthTokenClient googleAuthTokenClient;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.token-url}")
    private String kakaoTokenUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirectUrl}")
    private String redirectUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${google.token-url}")
    private String googleTokenUrl;

    @Value("${google.user-api-url}")
    private String googleUserApiUrl;

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;

    @Value("${google.redirectUrl}")
    private String googleRedirectUrl;

    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={여기에 REST API KEY를 입력해주세요}&redirect_uri=http://localhost:8080/callback
    private UserTokenDTO getKakaoAccessToken(String code){
        try {
            return client.getToken(new URI(kakaoTokenUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("토큰 발급 오류입니다");
            return UserTokenDTO.fail();
        }
    }

    public String kakaoLogin(String code){
        UserTokenDTO userToken = getKakaoAccessToken(code);
        UserAccountDTO userAccount = null;

        try {
            userAccount = client.getInfo(new URI(kakaoUserApiUrl), userToken.getTokenType() + " " + userToken.getAccessToken()).getUserAccount();
        } catch (Exception e) {
            log.error("유저 정보 확인 오류입니다");
        }

        User user = userService.checkUser(userAccount);

        return jwtTokenProvider.create(user);
    }

    private GoogleTokenDTO getGoogleAccessToken(String code){
        String decodedCode = "";
        try {
            decodedCode = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
        }
        try {
            return googleAuthTokenClient.getToken(GoogleAccessTokenRequestDTO.builder()
                    .clientId(googleClientId)
                    .clientSecret(googleClientSecret)
                    .redirectUri(googleRedirectUrl)
                    .code(decodedCode)
                    .grantType("authorization_code")
                    .build());
        } catch (Exception e) {
            log.error(e.toString());
            log.error("토큰 발급 오류입니다");
            return GoogleTokenDTO.fail();
        }
    }

    @Transactional
    public String googleLogin(String code){
        GoogleTokenDTO googleTokenDTO = getGoogleAccessToken(code);
        GoogleAccountDTO userAccount = null;

        String decodedCode = "";
        try {
            decodedCode = java.net.URLDecoder.decode(googleTokenDTO.getAccessToken(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
        }
        try {
            userAccount = googleAuthUserClient.getInfo(googleTokenDTO.getTokenType() + " " + decodedCode);
        } catch (Exception e) {
            log.error(e.toString());
            log.error("유저 정보 확인 오류입니다");
        }

        User user = userService.checkUser(userAccount);

        String jwtToken = jwtTokenProvider.create(user);
        System.out.println("jwtToken: " + jwtToken);
        return jwtToken;
    }
}
