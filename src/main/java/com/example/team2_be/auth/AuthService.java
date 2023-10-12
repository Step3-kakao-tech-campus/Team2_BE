package com.example.team2_be.auth;

import com.example.team2_be.auth.dto.UserAccountDTO;
import com.example.team2_be.auth.dto.UserTokenDTO;
import com.example.team2_be.core.security.JwtTokenProvider;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthClient client;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.token-url}")
    private String kakaoTokenUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={여기에 REST API KEY를 입력해주세요}&redirect_uri=http://localhost:8080/callback
    private UserTokenDTO getKakaoAccessToken(String code){
        try {
            return client.getToken(new URI(kakaoTokenUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("토큰 발급 오류입니다");
            return UserTokenDTO.fail();
        }
    }

    public String kakaoLogin(String code){
        UserTokenDTO userToken = getKakaoAccessToken(code);
        UserAccountDTO userAccount = null;

        try {
            userAccount = client.getInfo(new URI(kakaoUserApiUrl), userToken.getTokenType() + " " + userToken.getAccessToken()).getUserAccount();
        } catch (Exception e) {
            log.error("유저 정보 확인 오류입니다");
        }

        User user = userService.checkUser(userAccount);

        return jwtTokenProvider.create(user);
    }
}
