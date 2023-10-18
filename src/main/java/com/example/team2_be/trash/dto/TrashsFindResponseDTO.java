package com.example.team2_be.trash.dto;

import com.example.team2_be.trash.Trash;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TrashsFindResponseDTO {
    private Long albumId;
    private List<TrashDTO> Trashs;


    public TrashsFindResponseDTO (Long albumId, List<Trash> trashs){
        this.albumId = albumId;
        this.Trashs = trashs.stream()
                .map(TrashDTO::new)
                .collect((Collectors.toList()));
    }

    @Getter
    public static class TrashDTO{
        private Long trashId;
        private Long albumPageId;
        private LocalDateTime createAt;
        private LocalDateTime deleteAt;

        public TrashDTO(Trash trash) {
            this.trashId = trash.getId();
            this.albumPageId = trash.getAlbumPage().getId();
            this.createAt = trash.getCreateAt();
            this.deleteAt = trash.getDeleteAt();
        }
    }
}

