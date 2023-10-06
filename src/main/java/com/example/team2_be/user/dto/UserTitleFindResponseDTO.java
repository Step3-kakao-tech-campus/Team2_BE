package com.example.team2_be.user.dto;

import com.example.team2_be.title.collection.Collection;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserTitleFindResponseDTO {
    private List<TitleDTO> titles;

    public UserTitleFindResponseDTO(List<Collection> collections) {
        this.titles = collections.stream()
                .map(TitleDTO::new)
                .collect(Collectors.toList());
    }
}