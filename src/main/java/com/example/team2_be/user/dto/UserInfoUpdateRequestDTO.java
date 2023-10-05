package com.example.team2_be.user.dto;

import lombok.Getter;

@Getter
public class UserInfoUpdateRequestDTO {
    // 요청에 담긴 데이터의 유효성 판별 필요
    private String newNickname;
}
