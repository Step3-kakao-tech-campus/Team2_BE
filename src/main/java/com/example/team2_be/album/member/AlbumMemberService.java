package com.example.team2_be.album.member;

import com.example.team2_be.album.Album;
import com.example.team2_be.album.AlbumJPARepository;
import com.example.team2_be.core.error.exception.Exception404;
import com.example.team2_be.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlbumMemberService {

    private final AlbumJPARepository albumJPARepository;
    private final AlbumMemberJPARepository albumMemberJPARepository;

    public void addUser(User user, Long albumId){
        // 앨범이 없을 경우 예외 처리, 후에 추가 수정
        Album album = albumJPARepository.findById(albumId)
                .orElseThrow(() -> new Exception404("해당 앨범이 존재하지 않습니다."));

        AlbumMember albumMember = albumMemberJPARepository.findByUserAndGroup(user.getId(), albumId);
        if(albumMember == null) {
            albumMember = AlbumMember.builder()
                    .user(user)
                    .group(album)
                    .build();
            albumMemberJPARepository.save(albumMember);
        }
    }
}
