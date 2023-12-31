package com.example.team2_be.core.config;

import com.example.team2_be.core.security.CustomUserDetailsService;
import com.example.team2_be.core.security.JwtAuthenticationFilter;
import com.example.team2_be.core.security.JwtTokenProvider;
import com.example.team2_be.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final UserJPARepository userJPARepository;
    private final CustomUserDetailsService customUserDetailsService;

    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {


        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder.addFilter(new JwtAuthenticationFilter(customUserDetailsService, authenticationManager, jwtTokenProvider, userJPARepository, redisTemplate));
            super.configure(builder);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final String[] swaggerPermitUrls = {
                // swagger v2
                "/api/v2/api-docs",
                "/api/swagger-resources",
                "/api/swagger-resources/**",
                "/api/configuration/ui",
                "/api/configuration/security",
                "/api/swagger-ui.html",
                "/api/webjars/**",
                // swagger v3
                "/api/v3/api-docs/**",
                "/api/swagger-ui/**"
        };

        http
                .csrf().disable()
                .cors().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new CustomSecurityFilterManager())
                .and()
                .headers().frameOptions().sameOrigin();

        http
                .authorizeRequests(
                        authorize -> authorize
                        .antMatchers(swaggerPermitUrls).permitAll()
                        .antMatchers("/api/auth/**").permitAll()
                );
        return http.build();
    }
}
