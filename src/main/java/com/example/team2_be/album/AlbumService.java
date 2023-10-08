package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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



}
