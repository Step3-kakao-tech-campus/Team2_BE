package com.example.team2_be.user;

import lombok.Getter;

class UserRequest {
    @Getter
    public static class UpdateDTO {
        // 요청에 담긴 데이터의 유효성 판별 필요
        private String newNickname;
    }
}
