package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.kakao.KakaoInfoDTO;
import com.example.team2_be.auth.dto.kakao.KakaoTokenDTO;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@FeignClient(name = "kakaoAuth", configuration = AuthFeignConfig.class)
public interface KakaoAuthClient {
    @GetMapping
    KakaoInfoDTO getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    KakaoTokenDTO getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                           @RequestParam("client_secret") String secretKey,
                           @RequestParam("redirect_uri") String redirectUrl,
                           @RequestParam("code") String code,
                           @RequestParam("grant_type") String grantType);
}