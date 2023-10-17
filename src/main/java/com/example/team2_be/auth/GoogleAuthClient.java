package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.google.GoogleAccessTokenRequestDTO;
import com.example.team2_be.auth.dto.google.GoogleAccountDTO;
import com.example.team2_be.auth.dto.google.GoogleTokenDTO;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(name = "googleAuth", configuration = AuthFeignConfig.class)
public interface GoogleAuthClient {
    @GetMapping
    GoogleAccountDTO getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    GoogleTokenDTO getToken(URI baseUrl, @RequestBody GoogleAccessTokenRequestDTO tokenRequest);
}