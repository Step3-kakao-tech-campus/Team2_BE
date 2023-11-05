package com.example.team2_be.album.page;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.team2_be.album.Album;
import com.example.team2_be.album.AlbumJPARepository;
import com.example.team2_be.album.page.dto.AlbumPageRequestDTO;
import com.example.team2_be.album.page.dto.AlbumPageRequestDTO.AssetDTO;
import com.example.team2_be.album.page.image.AlbumPageImage;
import com.example.team2_be.album.page.image.AlbumPageImageJPARepository;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumPageService {
    private final AlbumJPARepository albumJPARepository;
    private final AlbumPageJPARepository albumPageJPARepository;
    private final AlbumPageImageJPARepository albumPageImageJPARepository;
    private final AmazonS3Client amazonS3Client;

    public void createPage(AlbumPageRequestDTO requestDTO, Long albumId) throws IOException {
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
        return albumJPARepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("해당 id값을 가진 앨범을 찾을 수 없습니다. : " + albumId));
    }

    private void createImage(AlbumPage albumPage, Map<String, AssetDTO> assetDTOMap) throws IOException {
        for (AssetDTO assetDTO : assetDTOMap.values()) {
            uploadImage(assetDTO);
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

    private void uploadImage(AssetDTO assetDTO) throws IOException {
        File file = getImageFromBase64(assetDTO.getSrc(), assetDTO.getFileName());
        amazonS3Client.putObject(new PutObjectRequest("kakaotechcampust-step3-nemobucket", assetDTO.getFileName(), file));
        file.delete();
    }

    private File getImageFromBase64(String src, String fileName) throws IOException {
        String base64Image = src.split(",")[1];
        byte[] data = DatatypeConverter.parseBase64Binary(base64Image);
        File file = new File(fileName);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        }
        return file;
    }
}
