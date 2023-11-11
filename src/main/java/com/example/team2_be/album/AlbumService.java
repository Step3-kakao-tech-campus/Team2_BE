package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.album.dto.AlbumFindAllResponseDTO;
import com.example.team2_be.album.dto.AlbumUpdaterequestDTO;
import com.example.team2_be.album.member.AlbumMember;
import com.example.team2_be.album.member.AlbumMemberJPARepository;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AlbumService {
    private final AlbumJPARepository albumJPARepository;
    private final UserJPARepository userJPARepository;
    private final AlbumMemberJPARepository albumMemberJPARepository;

    @Transactional
    public Album createAlbum(AlbumCreateRequestDTO requestDTO){

        Album newAlbum = Album.builder()
                .albumName(requestDTO.getAlbumName())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
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
        String updatedDescription = requestDTO.getDescription() != null ? requestDTO.getDescription() : album.getDescription();
        String updatedImage = requestDTO.getImage() != null ? requestDTO.getImage() : album.getImage();

        album.updateAlbum(updatedAlbumName,updatedDescription,updatedImage);

        return album;
    }

    // 앨범 조회 기능
    public AlbumFindAllResponseDTO findAllAlbum (Long userId){
        User findUser = userJPARepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        List<AlbumMember> members = albumMemberJPARepository.findAllByUserId(userId);
        List<Album> albums = members.stream()
                .map(AlbumMember::getAlbum)
                .collect(Collectors.toList());

        return new AlbumFindAllResponseDTO(albums);
    }
}
