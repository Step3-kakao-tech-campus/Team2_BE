package com.example.team2_be.core.error.exception;

import com.example.team2_be.core.utils.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){
        return new ResponseEntity<>(e.getBody(), e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownServerError(Exception e) {
        ApiUtils.ApiResult<?> apiResult = ApiUtils.error(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);

        //error 테이블을 생성하고 의도치 않은 에러에 대해 관리할 필요가 있음.

        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
