package com.example.team2_be.auth.oauth2.service;

import com.example.team2_be.auth.oauth2.CustomOAuth2User;
import com.example.team2_be.auth.oauth2.OAuthAttributes;
import com.example.team2_be.user.Role;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 소셜 로그인 시 키 값
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // 소셜 로그인에서 api가 제공하는 유저 정보
        Map<String, Object> attributes = oauth2User.getAttributes();

        // 소셜 타입에 따라 유저 정보 객체 생성
        OAuthAttributes extractAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, attributes);
        User createdUser = getUser(extractAttributes);

        return new CustomOAuth2User(Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().toString())),
                attributes,
                extractAttributes.getAttributeKey(),
                createdUser.getRole());
    }

    private User getUser(OAuthAttributes attributes){
        User findUser = userRepository.findByEmail(attributes.getOauth2UserInfo().getEmail());

        // userId 부분에 관해 조율 필요
        // 처음 로그인한 회원의 경우
        if(findUser == null){
            findUser = User.builder()
                    .userId("")
                    .password("")
                    .email(attributes.getOauth2UserInfo().getEmail())
                    .nickname(attributes.getOauth2UserInfo().getNickname())
                    .image(attributes.getOauth2UserInfo().getImageUrl())
                    .role(Role.ROLE_USER)
                    .createAt(LocalDateTime.now())
                    .build();
            userRepository.save(findUser);
        }
        return findUser;
    }
}
