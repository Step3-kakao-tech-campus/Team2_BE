package com.example.team2_be.core.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalSeverErrorException extends ApiException {
    public InternalSeverErrorException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
