package com.example.team2_be.core.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ApiUtils {
    @Getter
    @AllArgsConstructor
    public static class ApiResult<T> {
        private final boolean success;
        private final T response;
        private final ApiError error;
    }

    @Getter
    @AllArgsConstructor
    public static class ApiError {
        private final String message;
        private final int status;
    }
}

