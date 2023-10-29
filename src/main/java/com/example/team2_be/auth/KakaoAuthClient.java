package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.kakao.KakaoAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.kakao.KakaoAccountDTO;
import com.example.team2_be.auth.dto.kakao.KakaoTokenDTO;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(name = "kakaoAuth", configuration = AuthFeignConfig.class)
public interface KakaoAuthClient {
    @GetMapping
    KakaoAccountDTO getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    KakaoTokenDTO getToken(URI baseUrl, @RequestBody KakaoAccessTokenRequestDTO requestDTO);
}