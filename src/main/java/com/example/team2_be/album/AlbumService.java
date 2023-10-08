package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.album.dto.AlbumUpdaterequestDTO;
import com.example.team2_be.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlbumService {
    private final AlbumJPARepository albumJPARepository;

    //앨범 생성 기능
    public Album createAlbum(AlbumCreateRequestDTO requestDTO, User user){
        Album newAlbum = Album.builder()
                .albumName(requestDTO.getAlbumName())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
                .category(requestDTO.getCategory())
                .createAt(LocalDateTime.now())
                .build();
        albumJPARepository.save(newAlbum);

        return newAlbum;
    }

    public Album updateAlbum(AlbumUpdaterequestDTO requestDTO, User user, Long id) {
        Album album = albumJPARepository.findByAlbumId(id);
        //유저 확인,,필요

        String updatedAlbumName = requestDTO.getAlbumName() != null ? requestDTO.getAlbumName() : album.getAlbumName();
        String updatedDescription = requestDTO.getDescription() != null ? requestDTO.getDescription() : album.getDescription();
        String updatedImage = requestDTO.getImage() != null ? requestDTO.getImage() : album.getImage();

        album.update(updatedAlbumName,updatedDescription,updatedImage);

        return album;
    }

}
