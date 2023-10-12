package com.example.team2_be.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    @Value("${kakao.token-url}")
    private String kakaoTokenUrl;

    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={여기에 REST API KEY를 입력해주세요}&redirect_uri=http://localhost:8080/callback
    public String getKakaoAccesToken(String code){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", restapiKey);
        params.add("redirect_uri", redirectUrl);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(kakaoAuthUrl, request, String.class);

        // 2. 엑세스 토큰으로부터 사용자 정보 가져오기

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(responseEntity.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonNode.get("access_token").toString();
    }
}
