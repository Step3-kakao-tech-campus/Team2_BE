package com.example.team2_be.album.page.dto;

import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AlbumPageUpdateRequestDTO {
    private Map<String, Object> shapes;

    private Map<String, Object> bindings;

    private Map<String, AssetUpdateDTO> assets;

    @NotEmpty
    private String capturePage;

    public AlbumPageUpdateRequestDTO(Map<String, Object> shapes,
                                     Map<String, Object> bindings,
                                     Map<String, AssetUpdateDTO> assets,
                                     String capturePage)
    {
        this.shapes = shapes;
        this.bindings = bindings;
        this.assets = assets;
        this.capturePage = capturePage;
    }

    @Getter
    public static class AssetUpdateDTO {
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

        public AssetUpdateDTO(String id, String type, String fileName, String src, @NotNull double[] size) {
            this.id = id;
            this.type = type;
            this.fileName = fileName;
            this.src = src;
            this.size = size;
        }
    }
}
