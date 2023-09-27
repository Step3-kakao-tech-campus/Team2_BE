package com.example.team2_be.auth.oauth2;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private final String attributeKey;
    // 소셜 타입 별 유저 정보
    private final OAuth2UserInfo oauth2UserInfo;

    @Builder
    public OAuthAttributes(String attributeKey, OAuth2UserInfo oauth2UserInfo){
        this.attributeKey = attributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    // 소셜 타입 별 OAuthAttributes 객체 반환
    public static OAuthAttributes of(String socialType, String userAttributeKey, Map<String, Object> attributes){
        if(socialType.equals("kakao")){
            return ofKakao(userAttributeKey, attributes);
        }
        else {
            return ofGoogle(userAttributeKey, attributes);
        }
    }

    private static OAuthAttributes ofKakao(String userAttributeKey, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .attributeKey(userAttributeKey)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    private static OAuthAttributes ofGoogle(String userAttributeKey, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .attributeKey(userAttributeKey)
                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }
}
