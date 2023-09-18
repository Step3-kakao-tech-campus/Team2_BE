package com.example.team2_be.auth;

import lombok.Getter;

import javax.validation.constraints.Email;

class AuthRequest {
    @Getter
    public static class SendMailDTO {
        @Email
        private String email;
    }
}
