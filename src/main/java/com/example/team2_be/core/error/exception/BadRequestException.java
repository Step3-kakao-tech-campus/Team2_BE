package com.example.team2_be.core.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
