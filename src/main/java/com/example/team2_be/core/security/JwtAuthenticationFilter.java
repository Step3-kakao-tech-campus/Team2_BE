package com.example.team2_be.core.security;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.example.team2_be.user.Role;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private final CustomUserDetailsService customUserDetailsService;
    private static final String JWT_TOKEN = "JWT_TOKEN:";
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final UserJPARepository userJPARepository;

    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserJPARepository userJPARepository, RedisTemplate<String, Object> redisTemplate) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userJPARepository = userJPARepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(jwtTokenProvider.HEADER);

        if (jwt == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            DecodedJWT decodedJWT = jwtTokenProvider.verify(jwt);
            String email = decodedJWT.getClaim("sub").asString();
            Long id = decodedJWT.getClaim("id").asLong();
//            User user = userJPARepository.findById(id).orElseThrow(
//                    () -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
            UserDetails myUserDetails = customUserDetailsService.loadUserByUsername(email);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            myUserDetails,
                            myUserDetails.getPassword(),
                            myUserDetails.getAuthorities()
                    );
            String key = JWT_TOKEN + id;
            Object storedToken = redisTemplate.opsForValue().get(key);

            // 로그인 여부 체크
            if (Boolean.TRUE.equals(redisTemplate.hasKey(key)) && storedToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("디버그 : 인증 객체 만들어짐");
            }
        } catch (SignatureVerificationException sve) {
            log.error("토큰 검증 실패");
        } catch (TokenExpiredException tee) {
            log.error("토큰 만료됨");
        } finally {
            chain.doFilter(request, response);
        }
    }
}
