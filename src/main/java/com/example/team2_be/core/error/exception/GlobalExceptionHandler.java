package com.example.team2_be.core.error.exception;

import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.error.Error;
import com.example.team2_be.error.ErrorJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorJPARepository errorJPARepository;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){
        return new ResponseEntity<>(e.getBody(), e.getStatus());
    }

    @Transactional
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownServerError(Exception e) {
        ApiUtils.ApiResult<?> apiResult = ApiUtils.error(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);

        saveError(e);

        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void saveError(Exception e) {
        Error error = Error.builder()
                .message(e.getMessage())
                .stackTrace(filterStackTraceOnException(e).toString())
                .build();
        errorJPARepository.save(error);
    }

    private StringBuilder filterStackTraceOnException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();

        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().contains("com.example.team2_be")) {
                stringBuilder.append(element).append("\n");
            }
        }
        return stringBuilder;
    }
}
