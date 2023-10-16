package com.example.team2_be.auth.dto.google;

import com.example.team2_be.auth.dto.UserAccountDTO;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccountDTO extends UserAccountDTO {
    private String id;
    private String pictureUrl;

    @JsonSetter("name")
    private void setNickname(String name) {
        this.nickname = name;
    }
}