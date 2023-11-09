package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.UserAccountDTO;
import com.example.team2_be.auth.dto.google.GoogleAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.google.GoogleAccountDTO;
import com.example.team2_be.auth.dto.google.GoogleTokenDTO;
import com.example.team2_be.auth.dto.kakao.KakaoAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.kakao.KakaoTokenDTO;
import com.example.team2_be.core.error.exception.*;
import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.security.JwtTokenProvider;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final KakaoAuthClient kakaoAuthClient;
    private final GoogleAuthClient googleAuthClient;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${kakao.rest-api-key}")
    private String kakaoRrestapiKey;

    @Value("${kakao.redirect-url}")
    private String kakaoRedirectUrl;

    @Value("${kakao.token-url}")
    private String kakaoTokenUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserUrl;

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;

    @Value("${google.redirect-url}")
    private String googleRedirectUrl;

    @Value("${google.token-url}")
    private String googleTokenUrl;

    @Value("${google.user-api-url}")
    private String googleUserUrl;


    private KakaoTokenDTO getKakaoAccessToken(String code){
        try {
            return kakaoAuthClient.getToken(URI.create(kakaoTokenUrl), KakaoAccessTokenRequestDTO.builder()
                    .clientId(kakaoRrestapiKey)
                    .code(code)
                    .redirectUri(kakaoRedirectUrl)
                    .grantType("authorization_code")
                    .build());
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new BadRequestException("잘못된 요청입니다");
                case 401:
                    throw new UnauthorizedException("인증되지 않은 사용자입니다");
                case 403:
                    throw new ForbiddenException("접근이 허용되지 않습니다");
                case 404:
                    throw new NotFoundException("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new InternalSeverErrorException("토큰 발급 오류입니다");
            }
        }
        catch (Exception e) {
            throw new InternalSeverErrorException("토큰 발급 오류입니다");
        }
    }

    public String kakaoLogin(String code){
        KakaoTokenDTO userToken = getKakaoAccessToken(code);
        UserAccountDTO userAccount = null;

        try {
            userAccount = kakaoAuthClient.getInfo(URI.create(kakaoUserUrl), userToken.getTokenType() + " " + userToken.getAccessToken());
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new BadRequestException("잘못된 요청입니다");
                case 401:
                    throw new UnauthorizedException("인증되지 않은 사용자입니다");
                case 403:
                    throw new ForbiddenException("접근이 허용되지 않습니다");
                case 404:
                    throw new NotFoundException("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new InternalSeverErrorException("유저 정보 확인 오류입니다");
            }
        }
        catch (Exception e) {
            throw new InternalSeverErrorException("유저 정보 확인 오류입니다");
        }

        User user = userService.getUser(userAccount);
        String token = jwtTokenProvider.create(user);
        redisTemplate.opsForValue().set("JWT_TOKEN:" + user.getId(), token, JwtTokenProvider.EXP);

        return token;
    }

    @Transactional
    public String googleLogin(String code){
        GoogleTokenDTO googleTokenDTO = getGoogleAccessToken(code);
        GoogleAccountDTO userAccount = null;

        try {
            userAccount = googleAuthClient.getInfo(URI.create(googleUserUrl), googleTokenDTO.getTokenType() + " " + decoding(googleTokenDTO.getAccessToken()));
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new BadRequestException("잘못된 요청입니다");
                case 401:
                    throw new UnauthorizedException("인증되지 않은 사용자입니다");
                case 403:
                    throw new ForbiddenException("접근이 허용되지 않습니다");
                case 404:
                    throw new NotFoundException("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new InternalSeverErrorException("유저 정보 확인 오류입니다");
            }
        }
        catch (Exception e) {
            throw new InternalSeverErrorException("유저 정보 확인 오류입니다");
        }

        User user = userService.getUser(userAccount);
        String token = jwtTokenProvider.create(user);
        redisTemplate.opsForValue().set("JWT_TOKEN:" + user.getId(), token, JwtTokenProvider.EXP);

        return token;
    }

    private GoogleTokenDTO getGoogleAccessToken(String code){
        try {
            return googleAuthClient.getToken(URI.create(googleTokenUrl), GoogleAccessTokenRequestDTO.builder()
                    .clientId(googleClientId)
                    .clientSecret(googleClientSecret)
                    .redirectUri(googleRedirectUrl)
                    .code(decoding(code))
                    .grantType("authorization_code")
                    .build());
        } catch (HttpStatusCodeException e){
            switch (e.getStatusCode().value()){
                case 400:
                    throw new BadRequestException("잘못된 요청입니다");
                case 401:
                    throw new UnauthorizedException("인증되지 않은 사용자입니다");
                case 403:
                    throw new ForbiddenException("접근이 허용되지 않습니다");
                case 404:
                    throw new NotFoundException("해당 사용자를 찾을 수 없습니다");
                default:
                    throw new InternalSeverErrorException("토큰 발급 오류입니다");
            }
        }
        catch (Exception e) {
            System.out.println(e);
            throw new InternalSeverErrorException("토큰 발급 오류입니다");
        }
    }

    @Transactional
    public void logout(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            User user = ((CustomUserDetails) principal).getUser();
            if (redisTemplate.opsForValue().get("JWT_TOKEN:" + user.getId()) != null) {
                redisTemplate.delete("JWT_TOKEN:" + user.getId());
            }
        }
    }

    private String decoding(String code){
        String decodedCode = "";
        try {
            decodedCode = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new BadRequestException("잘못된 요청입니다");
        }
        return decodedCode;
    }
}
