package com.example.team2_be.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserAccountDTO {
    protected String email;
    protected String nickname;

    public UserAccountDTO(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}