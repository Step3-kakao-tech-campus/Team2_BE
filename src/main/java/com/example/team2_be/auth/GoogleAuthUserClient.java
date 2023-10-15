package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.google.GoogleAccountDTO;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "googleAuthUser", url = "${google.user-api-url}", configuration = AuthFeignConfig.class)
public interface GoogleAuthUserClient {
    @GetMapping
    GoogleAccountDTO getInfo(@RequestHeader("Authorization") String accessToken);
}
