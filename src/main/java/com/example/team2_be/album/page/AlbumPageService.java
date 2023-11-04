package com.example.team2_be.album.page;

import com.example.team2_be.album.Album;
import com.example.team2_be.album.AlbumJPARepository;
import com.example.team2_be.album.page.dto.AlbumPageRequestDTO;
import com.example.team2_be.album.page.dto.AlbumPageRequestDTO.AssetDTO;
import com.example.team2_be.album.page.image.AlbumPageImage;
import com.example.team2_be.album.page.image.AlbumPageImageJPARepository;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumPageService {
    private final AlbumJPARepository albumJPARepository;
    private final AlbumPageJPARepository albumPageJPARepository;
    private final AlbumPageImageJPARepository albumPageImageJPARepository;

    public void createPage(AlbumPageRequestDTO requestDTO, Long albumId) throws JsonProcessingException {
        Map<String, Object> shapesMap = requestDTO.getShapes();
        Map<String, Object> bindingsMap = requestDTO.getBindings();
        Map<String, AssetDTO> assetDTOMap = requestDTO.getAssets();

        ObjectMapper objectMapper = new ObjectMapper();
        String shapes = objectMapper.writeValueAsString(shapesMap);
        String bindings = objectMapper.writeValueAsString(bindingsMap);

        AlbumPage albumPage = AlbumPage.builder()
                .album(findAlbum(albumId))
                .shapes(shapes)
                .bindings(bindings)
                .build();
        AlbumPage saveAlbumPage = albumPageJPARepository.save(albumPage);
        createImage(saveAlbumPage, assetDTOMap);
    }

    private Album findAlbum(Long albumId) {
        Album album = albumJPARepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("해당 id값을 가진 앨범을 찾을 수 없습니다. : " + albumId));
        return album;
    }

    private void createImage(AlbumPage albumPage, Map<String, AssetDTO> assetDTOMap) {
        for(AssetDTO assetDTO : assetDTOMap.values()){
            AlbumPageImage albumPageImage = AlbumPageImage.builder()
                    .albumPage(albumPage)
                    .assetId(assetDTO.getId())
                    .fileName(assetDTO.getFileName())
                    .type(assetDTO.getType())
                    .xSize(assetDTO.getSize()[0])
                    .ySize(assetDTO.getSize()[1])
                    .build();
            albumPageImageJPARepository.save(albumPageImage);
        }
    }
}
