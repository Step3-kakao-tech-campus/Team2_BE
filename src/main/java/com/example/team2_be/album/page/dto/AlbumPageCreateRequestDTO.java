package com.example.team2_be.album.page.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AlbumPageCreateRequestDTO {
    @NotEmpty
    private Long albumId;

    @Builder
    public AlbumPageCreateRequestDTO(Long albumId) {
        this.albumId = albumId;
    }
}
