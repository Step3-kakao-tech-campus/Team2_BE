package com.example.team2_be.user;

import lombok.Getter;

class UserResponse {
    @Getter
    public static class FindDTO {
        private final Long id;
        private final String nickname;
        private final String email;
        //칭호 관련 응답은 ERD 조율 후 작성

        public FindDTO(User user) {
            this.id = user.getId();
            this.nickname = user.getNickname();
            this.email = user.getEmail();
        }
    }
}
