package com.example.team2_be.album.dto;

import com.example.team2_be.album.Category;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class AlbumCreateRequestDTO {
    @NonNull
    private Category category;
    @NonNull
    private String albumName;
    @NonNull
    private String description;
    @NonNull
    private String image;
}
