package com.example.team2_be.user.dto;

import com.example.team2_be.title.collection.Collection;
import com.example.team2_be.user.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class UserInfoFindResponseDTO {
    private final Long id;
    private final String nickname;
    private final String email;
    private final List<TitleDTO> titles;
    //칭호 관련 응답은 ERD 조율 후 작성

    public UserInfoFindResponseDTO(User user, List<Collection> collections) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.titles = collections.stream()
                .map(TitleDTO::new)
                .collect(Collectors.toList());
    }

    public static class TitleDTO {
        private final Long titleId;
        private final String titleName;

        public TitleDTO(Collection collection) {
            this.titleId = collection.getId();
            this.titleName = collection.getTitle().getTitleName();
        }
    }
}
