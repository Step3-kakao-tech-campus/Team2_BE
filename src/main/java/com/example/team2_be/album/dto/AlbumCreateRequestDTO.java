package com.example.team2_be.album.dto;

import com.example.team2_be.album.Category;
import lombok.Getter;

@Getter
public class AlbumCreateRequestDTO {
    private Category category;
    private String albumName;
    private String Description;
    private String image;
}
