//package com.example.team2_be.auth.security.jwt;
//
//
//import java.io.IOException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RequiredArgsConstructor
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
////    @Autowired
////    UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String jwtHeader = ((HttpServletRequest)request).getHeader("Authorization"); //HTTP요청의 헤더 이름넣기..
//
//        //만약 jwtHeader 가 null 이거나 Bearer 로 시작하지 않으면 return; 으로 이후 로직을 실행시키지 않고 넘긴다.
//        if(jwtHeader == null || !jwtHeader.startsWith("Bearer ")){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //jwtHeader 가 제대로 된 형식이라면 토큰 앞의 Bearer 를 떼어내 token 변수에 담는다
//        String token = jwtHeader.replace("Bearer ", "");
//
//        Long userCode = null;
//
//        //token 을 비밀키로 복호화하는 동시에 개인 클레임에 넣어뒀던 id 값을 가져온다. 이 코드 자체가 인증과정이므로 이어지는 catch 문에서 Exception 처리를 해준다.
//        try {
//            userCode = JwtUtils.getUserEmailFromToken(token);
//
//        } catch (TokenExpiredException e) {
//            e.printStackTrace();
//            request.setAttribute(JwtProperties.HEADER_STRING, "토큰이 만료되었습니다.");
//        } catch (JWTVerificationException e) {
//            e.printStackTrace();
//            request.setAttribute(JwtProperties.HEADER_STRING, "유효하지 않은 토큰입니다.");
//        }
//
//        //(8)
//        request.setAttribute("userCode", userCode);
//
//        //(9)
//        filterChain.doFilter(request, response);
//    }
//}