package com.example.team2_be.album.page;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.team2_be.album.Album;
import com.example.team2_be.album.AlbumJPARepository;
import com.example.team2_be.album.page.dto.AlbumPageFindResponseDTO;
import com.example.team2_be.album.page.dto.AlbumPageUpdateRequestDTO;
import com.example.team2_be.album.page.dto.AlbumPageUpdateRequestDTO.AssetUpdateDTO;
import com.example.team2_be.album.page.image.AlbumPageImage;
import com.example.team2_be.album.page.image.AlbumPageImageJPARepository;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumPageService {
    private static final String IMAGE_DATA_DELIMITER = ",";
    private static final String BUKET_NAME = "kakaotechcampust-step3-nemobucket";
    private final AlbumJPARepository albumJPARepository;
    private final AlbumPageJPARepository albumPageJPARepository;
    private final AlbumPageImageJPARepository albumPageImageJPARepository;
    private final AmazonS3Client amazonS3Client;

    @Transactional
    public void updatePage(AlbumPageUpdateRequestDTO requestDTO, Long pageId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String shapes = objectMapper.writeValueAsString(requestDTO.getShapes());
        String bindings = objectMapper.writeValueAsString(requestDTO.getBindings());

        AlbumPage albumPage = findAlbumPageById(pageId);
        checkEmptyAssetDTOMap(requestDTO.getAssets(), albumPage);

        String capturePageImageFileName =
                albumPage.getAlbum().getId() + "번 앨범 내 " + albumPage.getId() + "번 앨범페이지 현재 상태 캡처 사진.jpg";
        uploadImageToS3(requestDTO.getCapturePage(), capturePageImageFileName);
        albumPage.updateAlbumPage(shapes, bindings, getImageUrl(capturePageImageFileName));
    }

    @Transactional
    public void createPage(Long albumId) {
        Album album = findAlbumById(albumId);

        AlbumPage albumPage = AlbumPage.builder()
                .album(album)
                .build();
        albumPageJPARepository.save(albumPage);
    }

    @Transactional
    public AlbumPageFindResponseDTO findPage(Pageable pageable) {
        Page<AlbumPage> albumPages = albumPageJPARepository.findAll(pageable);

        return new AlbumPageFindResponseDTO(albumPages.getContent());
    }

    private void checkEmptyAssetDTOMap(Map<String, AssetUpdateDTO> assetDTOMap, AlbumPage albumPage) throws IOException {
        if (assetDTOMap != null) {
            createPageImage(albumPage, assetDTOMap);
        }
    }

    private void createPageImage(AlbumPage albumPage, Map<String, AssetUpdateDTO> assetDTOMap) throws IOException {
        for (AssetUpdateDTO assetDTO : assetDTOMap.values()) {
            uploadImageToS3(assetDTO.getSrc(), assetDTO.getFileName());
            AlbumPageImage albumPageImage = AlbumPageImage.builder()
                    .assetId(assetDTO.getId())
                    .albumPage(albumPage)
                    .fileName(assetDTO.getFileName())
                    .type(assetDTO.getType())
                    .xSize(assetDTO.getSize()[0])
                    .ySize(assetDTO.getSize()[1])
                    .url(getImageUrl(assetDTO.getFileName()))
                    .build();
            albumPageImageJPARepository.save(albumPageImage);
        }
    }

    private Album findAlbumById(Long albumId) {
        return albumJPARepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("해당 id값을 가진 앨범을 찾을 수 없습니다. : " + albumId));
    }

    public AlbumPage findAlbumPageById(Long pageId) {
        return albumPageJPARepository.findById(pageId)
                .orElseThrow(() -> new NotFoundException("해당 id를 가진 앨범페이지를 찾을 수 없습니다." + pageId));
    }

    private void uploadImageToS3(String src, String fileName) throws IOException {
        File file = getImageFromBase64(src, fileName);
        amazonS3Client.putObject(new PutObjectRequest("kakaotechcampust-step3-nemobucket", fileName, file));
        file.delete();
    }

    private File getImageFromBase64(String src, String fileName) throws IOException {
        String base64Image = src.split(IMAGE_DATA_DELIMITER)[1];
        byte[] data = DatatypeConverter.parseBase64Binary(base64Image);
        File file = new File(fileName);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        }
        return file;
    }

    private String getImageUrl(String fileName) {
        URL url = amazonS3Client.getUrl(BUKET_NAME, fileName);
        return "" + url;
    }
}
