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

}
