package com.example.team2_be.album.dto;

import com.example.team2_be.album.Category;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AlbumCreateRequestDTO {
    @NotNull
    private Category category;
    @NotNull
    private String albumName;
    @NotNull
    private String description;
    @NotNull
    private String image;
}
