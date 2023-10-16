package com.example.team2_be.core.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
