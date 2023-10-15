package com.example.team2_be.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserAccountDTO {
    protected String nickname;
    protected String email;

    public static UserAccountDTO fail(){return null;}
}