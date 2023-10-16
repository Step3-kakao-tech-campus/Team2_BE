package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.UserAccountDTO;
import com.example.team2_be.auth.dto.google.GoogleAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.google.GoogleAccountDTO;
import com.example.team2_be.auth.dto.google.GoogleTokenDTO;
import com.example.team2_be.auth.dto.kakao.KakaoAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.kakao.KakaoTokenDTO;
import com.example.team2_be.core.error.exception.*;
import com.example.team2_be.core.security.JwtTokenProvider;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

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
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.rest-api-key}")
    private String kakaoRrestapiKey;

    @Value("${kakao.redirect-url}")
    private String kakaoRedirectUrl;

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;

    @Value("${google.redirect-url}")
    private String googleRedirectUrl;


    private KakaoTokenDTO getKakaoAccessToken(String code){
        try {
            return kakaoAuthTokenClient.getToken(KakaoAccessTokenRequestDTO.builder()
                    .clientId(kakaoRrestapiKey)
                    .code(code)
                    .redirectUri(kakaoRedirectUrl)
                    .grantType("authorization_code")
                    .build());
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new Exception400("잘못된 요청입니다");
                case 401:
                    throw new Exception401("인증되지 않은 사용자입니다");
                case 403:
                    throw new Exception403("접근이 허용되지 않습니다");
                case 404:
                    throw new Exception404("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new Exception500("토큰 발급 오류입니다");
            }
        }
        catch (Exception e) {
            throw new Exception500("토큰 발급 오류입니다");
        }
    }

    public String kakaoLogin(String code){
        KakaoTokenDTO userToken = getKakaoAccessToken(code);
        UserAccountDTO userAccount = null;

        try {
            userAccount = kakaoAuthUserClient.getInfo(userToken.getTokenType() + " " + userToken.getAccessToken());
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new Exception400("잘못된 요청입니다");
                case 401:
                    throw new Exception401("인증되지 않은 사용자입니다");
                case 403:
                    throw new Exception403("접근이 허용되지 않습니다");
                case 404:
                    throw new Exception404("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new Exception500("유저 정보 확인 오류입니다");
            }
        }
        catch (Exception e) {
            throw new Exception500("유저 정보 확인 오류입니다");
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
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new Exception400("잘못된 요청입니다");
                case 401:
                    throw new Exception401("인증되지 않은 사용자입니다");
                case 403:
                    throw new Exception403("접근이 허용되지 않습니다");
                case 404:
                    throw new Exception404("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new Exception500("유저 정보 확인 오류입니다");
            }
        }
        catch (Exception e) {
            throw new Exception500("유저 정보 확인 오류입니다");
        }

        User user = userService.getUser(userAccount);

        return jwtTokenProvider.create(user);
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
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new Exception400("잘못된 요청입니다");
                case 401:
                    throw new Exception401("인증되지 않은 사용자입니다");
                case 403:
                    throw new Exception403("접근이 허용되지 않습니다");
                case 404:
                    throw new Exception404("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new Exception500("토큰 발급 오류입니다");
            }
        }
        catch (Exception e) {
            throw new Exception500("토큰 발급 오류입니다");
        }
    }

    private String deCoding(String code){
        String decodedCode = "";
        try {
            decodedCode = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new Exception400("잘못된 요청입니다");
        }
        return decodedCode;
    }
}
