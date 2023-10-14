package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.GoogleAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.GoogleTokenDTO;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "googleAuthToken", url = "${google.token-url}", configuration = {AuthFeignConfig.class})
public interface GoogleAuthTokenClient {
    @PostMapping(consumes = "application/x-www-form-urlencoded")
    GoogleTokenDTO getToken(@RequestBody GoogleAccessTokenRequestDTO tokenRequest);
}
