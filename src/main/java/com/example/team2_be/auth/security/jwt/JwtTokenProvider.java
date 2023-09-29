package com.example.team2_be.auth.security.jwt;


import com.example.team2_be.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtTokenProvider {
    //JWT 토큰을 만듦
    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey) {
        //시크릿 키를 바탕으로 Key 객체 생성
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        //Base64 디코딩된 시크릿 키 값을 이용해 Key 객체를 생성
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(User user) { //여기에 들어와야 하는게 OAuth2 유저값
        // JWT 토큰 생성
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getUserId())
                .setHeader(createHeader())
                .setClaims(createClamis(user))
                .setExpiration(createExpireDateForOneYear())
                .signWith(key, SignatureAlgorithm.HS512);

        return builder.compact();
    }

    private static Map<String, Object> createHeader() {
        //Header 생성
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClamis(User user) { //여기에 들어와야 하는게 OAuth2 유저값
        // 공개 클레임에 사용자의 이메일과 역할을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getUserId());
        claims.put("role", user.getRole());
        return claims;
    }

    private static Date createExpireDateForOneYear() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }
}
