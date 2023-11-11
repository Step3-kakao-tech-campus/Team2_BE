package com.example.team2_be.album;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.album.dto.AlbumFindAllResponseDTO;
import com.example.team2_be.album.dto.AlbumUpdaterequestDTO;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserJPARepository;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AlbumService {
    private static final String PNG = ".png";
    private final AlbumJPARepository albumJPARepository;
    private final UserJPARepository userJPARepository;
    private final AmazonS3Client amazonS3Client;

    @Transactional
    public Album createAlbum(AlbumCreateRequestDTO requestDTO) throws IOException {
        uploadImageToS3(requestDTO.getImage(), requestDTO.getAlbumName() + PNG);
        String url = getImageUrl(requestDTO.getAlbumName()+PNG);

        Album newAlbum = Album.builder()
                .albumName(requestDTO.getAlbumName())
                .description(requestDTO.getDescription())
                .image(url)
                .category(requestDTO.getCategory())
                .build();
        albumJPARepository.save(newAlbum);

        return newAlbum;
    }

    @Transactional
    public Album updateAlbum(AlbumUpdaterequestDTO requestDTO, Long AlbumId) {
        Album album = albumJPARepository.findById(AlbumId)
                .orElseThrow(() -> new NotFoundException("해당 id값을 가진 앨범을 찾을 수 없습니다. : " + AlbumId));

        String updatedAlbumName = requestDTO.getAlbumName() != null ? requestDTO.getAlbumName() : album.getAlbumName();
        String updatedDescription =
                requestDTO.getDescription() != null ? requestDTO.getDescription() : album.getDescription();
        String updatedImage = requestDTO.getImage() != null ? requestDTO.getImage() : album.getImage();

        album.updateAlbum(updatedAlbumName, updatedDescription, updatedImage);

        return album;
    }

    // 앨범 조회 기능
    public AlbumFindAllResponseDTO findAllAlbum(Long userId) {
        User findUser = userJPARepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        List<Album> albums = albumJPARepository.findAllByUserId(userId);

        return new AlbumFindAllResponseDTO(albums);
    }

    private void uploadImageToS3(String src, String fileName) throws IOException {
        File file = getImageFromBase64(src, fileName);
        amazonS3Client.putObject(new PutObjectRequest("kakaotechcampust-step3-nemobucket", fileName, file));
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

    private String getImageUrl(String fileName) {
        URL url = amazonS3Client.getUrl("kakaotechcampust-step3-nemobucket", fileName);
        String urltext = "" + url;
        return urltext;
    }
}
