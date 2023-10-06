package com.example.team2_be.core.error.exception;

import com.example.team2_be.core.utils.ApiUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class Exception400 extends ApiException {
    public Exception400(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
