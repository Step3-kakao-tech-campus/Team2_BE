package com.example.team2_be.auth.security.jwt;

import com.example.team2_be.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.log4j.Log4j2;
import java.security.Key;

@Log4j2
public class JwtUtils {
    private static Key key ;
    //private static BasicLogger log;

    public JwtUtils(@Value("${jwt.secret-key}") String secretKey) {
        //시크릿 키를 바탕으로 Key 객체 생성
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        //Base64 디코딩된 시크릿 키 값을 이용해 Key 객체를 생성
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token).getBody();
    }

    private String getUserEmailFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("email");
    }

    private Role getRoleFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (Role) claims.get("role");
    }

    public static boolean isValidToken(String token) {
        //토큰 유효성 검사
        try {
            Claims claims = getClaimsFormToken(token);
            log.info("expireTime :" + claims.getExpiration());
            log.info("email :" + claims.get("email"));
            log.info("role :" + claims.get("role"));
            return true;

        } catch (ExpiredJwtException exception) {
            log.error("만료된 토큰입니다");
            return false;
        } catch (JwtException exception) {
            log.error("변조된 토큰입니다");
            return false;
        } catch (NullPointerException exception) {
            log.error("빈 토큰입니다");
            return false;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }


}
