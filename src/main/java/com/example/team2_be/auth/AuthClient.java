package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.UserInfo;
import com.example.team2_be.auth.dto.UserToken;
import com.example.team2_be.core.config.AuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "authClient", configuration = AuthFeignConfig.class)
public interface AuthClient {

    @PostMapping
    UserInfo getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    UserToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                       @RequestParam("redirect_uri") String redirectUrl,
                       @RequestParam("code") String code,
                       @RequestParam("grant_type") String grantType);
}