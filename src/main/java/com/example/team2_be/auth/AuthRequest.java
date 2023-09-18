package com.example.team2_be.auth;

import lombok.Getter;

import javax.validation.constraints.Email;

public class AuthRequest {
    @Getter
    public static class SendMailDTO {
        @Email
        private String email;
    }

    @Getter
    public static class CheckMailDTO {
        @Email
        private String email;
        private String authCode;
    }
}
