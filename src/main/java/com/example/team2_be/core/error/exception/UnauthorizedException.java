package com.example.team2_be.core.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// 인증 안됨
@Getter
public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}