package com.example.team2_be.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserAccountDTO {
    private ProfileDTO profile;
    private String email;
}
