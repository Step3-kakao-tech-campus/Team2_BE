package com.example.team2_be.album.page.dto;

import com.example.team2_be.album.page.image.AlbumPageImage;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AlbumPageFindResponseDTO {
    private final String shapes;
    private final String bindings;
    private final List<AssetFindDTO> assets;

    @Builder
    public AlbumPageFindResponseDTO(String shapes, String bindings, List<AssetFindDTO> assets) {
        this.shapes = shapes;
        this.bindings = bindings;
        this.assets = assets;
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
        public AssetFindDTO(AlbumPageImage albumPageImage, String url) {
            this.id = albumPageImage.getAssetId();
            this.type = albumPageImage.getType();
            this.fileName = albumPageImage.getFileName();
            this.url = url;
            this.size = new double[]{albumPageImage.getXSize(), albumPageImage.getYSize()};
        }
    }
}
