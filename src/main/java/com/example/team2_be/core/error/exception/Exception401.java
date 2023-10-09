package com.example.team2_be.core.error.exception;

import com.example.team2_be.core.utils.ApiUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 인증 안됨
@Getter
public class Exception401 extends ApiException {
    public Exception401(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}