package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.kakao.KakaoAccountDTO;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoAuthUser", url = "${kakao.user-api-url}", configuration = AuthFeignConfig.class)
public interface KakaoAuthUserClient {
    @GetMapping
    KakaoAccountDTO getInfo(@RequestHeader("Authorization") String accessToken);
}