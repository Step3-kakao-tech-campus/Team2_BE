package com.example.team2_be.album.page.dto;

import com.example.team2_be.album.page.AlbumPage;
import com.example.team2_be.album.page.image.AlbumPageImage;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AlbumPageFindResponseDTO {
    private final List<AlbumPageFindDTO> albumPageFindDTOs;

    @Builder
    public AlbumPageFindResponseDTO(List<AlbumPage> albumPages) {
        this.albumPageFindDTOs = albumPages.stream()
                .map(AlbumPageFindDTO::new)
                .collect(Collectors.toList());
    }


    @Getter
    public static class AlbumPageFindDTO {
        private final String shapes;
        private final String bindings;
        private final List<AssetFindDTO> assets;

        @Builder
        public AlbumPageFindDTO(AlbumPage albumPage) {
            this.shapes = albumPage.getShapes();
            this.bindings = albumPage.getBindings();
            this.assets = albumPage.getAlbumPageImages().stream()
                    .map(albumPageImage -> AssetFindDTO.builder()
                            .albumPageImage(albumPageImage)
                            .build())
                    .collect(Collectors.toList());
        }

        @Getter
        public static class AssetFindDTO {
            @NotEmpty
            private String id;
            @NotEmpty
            private String type;
            @NotEmpty
            private String fileName;
            @NotEmpty
            private String url;
            @NotNull
            private double[] size;

            @Builder
            public AssetFindDTO(AlbumPageImage albumPageImage) {
                this.id = albumPageImage.getAssetId();
                this.type = albumPageImage.getType();
                this.fileName = albumPageImage.getFileName();
                this.url = albumPageImage.getUrl();
                this.size = new double[]{albumPageImage.getXSize(), albumPageImage.getYSize()};
            }
        }
    }

}
