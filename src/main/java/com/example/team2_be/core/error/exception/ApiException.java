package com.example.team2_be.core.error.exception;

import com.example.team2_be.core.utils.ApiUtils;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }

    public HttpStatus getStatus() {
        return null;
    }

    public ApiUtils.ApiResult<?> getBody() {
        return ApiUtils.error(getMessage(), getStatus());
    }
}
