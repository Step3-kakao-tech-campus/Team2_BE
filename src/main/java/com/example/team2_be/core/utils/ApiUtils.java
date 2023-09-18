package com.example.team2_be.core.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ApiUtils {
    @Getter
    @AllArgsConstructor
    public static class ApiError {
        private final String message;
        private final int status;
    }
}

