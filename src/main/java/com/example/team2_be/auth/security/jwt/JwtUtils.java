package com.example.team2_be.auth.security.jwt;

import com.example.team2_be.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

public class JwtUtils {
    private final Key key;

    public JwtUtils(@Value("${jwt.secret-key}") String secretKey) {
        //시크릿 키를 바탕으로 Key 객체 생성
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        //Base64 디코딩된 시크릿 키 값을 이용해 Key 객체를 생성
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private  Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token).getBody();
    }

}
