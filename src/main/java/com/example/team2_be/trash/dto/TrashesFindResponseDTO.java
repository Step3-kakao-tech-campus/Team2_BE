package com.example.team2_be.trash.dto;

import com.example.team2_be.trash.Trash;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TrashesFindResponseDTO {
    private Long albumId;
    private List<TrashDTO> trashes;

    public TrashesFindResponseDTO(Long albumId, List<Trash> trashes){
        this.albumId = albumId;
        this.trashes = trashes.stream()
                .map(TrashDTO::new)
                .collect((Collectors.toList()));
    }

    @Getter
    public static class TrashDTO{
        private Long trashId;
        private String image;
        private String deleter;
        private LocalDateTime createAt;
        private LocalDateTime deleteAt;

        public TrashDTO(Trash trash) {
            this.trashId = trash.getId();
            this.image = trash.getAlbumPage().getCapturePageUrl();
            this.deleter = trash.getUser().getNickname();
            this.createAt = trash.getCreateAt();
            this.deleteAt = trash.getDeleteAt();
        }
    }
}

