package com.example.team2_be.core.error.exception;

import org.springframework.http.HttpStatus;

public class MethodNotAllowedException extends ApiException{
    public MethodNotAllowedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }
}
