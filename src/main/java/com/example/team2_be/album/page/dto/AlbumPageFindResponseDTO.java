package com.example.team2_be.album.page.dto;

import com.example.team2_be.album.page.AlbumPage;
import com.example.team2_be.album.page.image.AlbumPageImage;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

public class AlbumPageFindResponseDTO {
    private final String shapes;

    private final String bindings;

    private final Map<String, AssetDTO> assets;

    public AlbumPageFindResponseDTO(AlbumPage albumPage, AlbumPageImage albumPageImage) {
        this.shapes = albumPage.getShapes();
        this.bindings = albumPage.getBindings();
        this.assets = (Map<String, AssetDTO>) new AssetDTO(albumPageImage);
    }

    @Getter
    public static class AssetDTO {
        @NotEmpty
        private String id;
        @NotEmpty
        private String type;
        @NotEmpty
        private String fileName;
        @NotEmpty
        private String src;
        @NotNull
        private double[] size;

        public AssetDTO(AlbumPageImage albumPageImage) {
            this.id = albumPageImage.getAssetId();
            this.type = albumPageImage.getType();
            this.fileName = albumPageImage.getFileName();
            this.src = "https://kakaotechcampust-step3-nemobucket.s3.ap-northeast-2.amazonaws.com/" + fileName;
            this.size = new double[] {albumPageImage.getXSize(), albumPageImage.getYSize()};
        }
    }
}
