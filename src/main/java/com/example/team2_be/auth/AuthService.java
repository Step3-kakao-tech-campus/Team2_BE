package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.*;
import com.example.team2_be.auth.dto.google.GoogleAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.google.GoogleAccountDTO;
import com.example.team2_be.auth.dto.google.GoogleTokenDTO;
import com.example.team2_be.auth.dto.kakao.KakaoAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.kakao.KakaoTokenDTO;
import com.example.team2_be.core.security.JwtTokenProvider;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final KakaoAuthUserClient kakaoAuthUserClient;
    private final KakaoAuthTokenClient kakaoAuthTokenClient;
    private final GoogleAuthUserClient googleAuthUserClient;
    private final GoogleAuthTokenClient googleAuthTokenClient;
    private final UserService userService;팅
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.rest-api-key}")
    private String kakaoRrestapiKey;

    @Value("${kakao.redirect-url}")
    private String kakaoRedirectUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;

    @Value("${google.redirect-url}")
    private String googleRedirectUrl;


    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={여기에 REST API KEY를 입력해주세요}&redirect_uri=http://localhost:8080/callback
    private KakaoTokenDTO getKakaoAccessToken(String code){
        try {
            return kakaoAuthTokenClient.getToken(KakaoAccessTokenRequestDTO.builder()
                    .clientId(kakaoRrestapiKey)
                    .code(code)
                    .redirectUri(kakaoRedirectUrl)
                    .grantType("authorization_code")
                    .build());
        } catch (Exception e) {
            log.error("토큰 발급 오류입니다");
            return KakaoTokenDTO.fail();
        }
    }

    public String kakaoLogin(String code){
        KakaoTokenDTO userToken = getKakaoAccessToken(code);
        UserAccountDTO userAccount = null;

        try {
            userAccount = kakaoAuthUserClient.getInfo(userToken.getTokenType() + " " + userToken.getAccessToken());
        } catch (Exception e) {
            log.error("유저 정보 확인 오류입니다");
        }

        User user = userService.getUser(userAccount);

        return jwtTokenProvider.create(user);
    }

    @Transactional
    public String googleLogin(String code){
        GoogleTokenDTO googleTokenDTO = getGoogleAccessToken(code);
        GoogleAccountDTO userAccount = null;

        try {
            userAccount = googleAuthUserClient.getInfo(googleTokenDTO.getTokenType() + " " + deCoding(googleTokenDTO.getAccessToken()));
        } catch (Exception e) {
            log.error(e.toString());
            log.error("유저 정보 확인 오류입니다");
        }

        User user = userService.getUser(userAccount);

        String jwtToken = jwtTokenProvider.create(user);
        System.out.println("jwtToken: " + jwtToken);
        return jwtToken;
    }

    private GoogleTokenDTO getGoogleAccessToken(String code){
        try {
            return googleAuthTokenClient.getToken(GoogleAccessTokenRequestDTO.builder()
                    .clientId(googleClientId)
                    .clientSecret(googleClientSecret)
                    .redirectUri(googleRedirectUrl)
                    .code(deCoding(code))
                    .grantType("authorization_code")
                    .build());
        } catch (Exception e) {
            log.error("토큰 발급 오류입니다");
            return GoogleTokenDTO.fail();
        }
    }

    private String deCoding(String code){
        String decodedCode = "";
        try {
            decodedCode = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
        }
        return decodedCode;
    }
}
