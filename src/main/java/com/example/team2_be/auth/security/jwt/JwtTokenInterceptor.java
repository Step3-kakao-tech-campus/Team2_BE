package com.example.team2_be.auth.security.jwt;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtTokenInterceptor implements HandlerInterceptor {
    // 토큰을 검증하도록 설정한 API에 대해 요청을 intercept하여 토큰의 유효성 검사를 진행
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {
        final String header = request.getHeader("Authorization");

        if (header != null) {
            final String token = JwtUtils.getTokenFromHeader(header);
            if (JwtUtils.isValidToken(token)) {
                return true;
            }
        }
        //유효성 검사에 실패하면 예외 API로 redirect
        response.sendRedirect("/error/unauthorized");
        return false;

    }

}