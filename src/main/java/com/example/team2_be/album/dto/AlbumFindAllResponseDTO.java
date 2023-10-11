package com.example.team2_be.album.dto;

import com.example.team2_be.album.Album;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AlbumFindAllResponseDTO {
    private List<AlbumDTO> albums;

    public AlbumFindAllResponseDTO(List<Album> albumList) {
        this.albums = albumList.stream()
                .map(AlbumDTO::new)
                .collect(Collectors.toList());
    }

    @Getter
    static class AlbumDTO {
        private Long id;
        private String image;
        private String albumName;
        private String description;

        public AlbumDTO(Album album) {
            this.id = album.getId();
            this.image = album.getImage();
            this.albumName = album.getAlbumName();
            this.description = album.getDescription();
        }
    }
}


