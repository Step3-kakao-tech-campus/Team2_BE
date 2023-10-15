package com.example.team2_be.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
public class UserInfoUpdateRequestDTO {
    @Setter
    @Size(min = 2, message = "닉네임은 두 글자 이상이어야 합니다.")
    private String newNickname;
}
