package com.example.team2_be.user.dto;

import com.example.team2_be.user.User;
import lombok.Getter;

@Getter
public class UserInfoFindResponseDTO {
    private final Long id;
    private final String nickname;
    private final String email;
    //칭호 관련 응답은 ERD 조율 후 작성

    public UserInfoFindResponseDTO(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
