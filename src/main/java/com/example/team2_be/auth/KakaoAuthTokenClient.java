package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.kakao.KakaoAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.kakao.KakaoTokenDTO;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kakaoAuthToken", url = "${kakao.token-url}", configuration = AuthFeignConfig.class)
public interface KakaoAuthTokenClient {
    @PostMapping
    KakaoTokenDTO getToken(@RequestBody KakaoAccessTokenRequestDTO requestDTO);
}