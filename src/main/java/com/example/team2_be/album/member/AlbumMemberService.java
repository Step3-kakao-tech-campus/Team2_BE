package com.example.team2_be.album.member;

import com.example.team2_be.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlbumMemberService {

    private final AlbumMemberJPARepository albumMemberJPARepository;

    public void addUser(User user, Long albumId){
        AlbumMember albumMember = albumMemberJPARepository.findByUserAndGroup(user.getId(), albumId);
        if(albumMember == null) {
            albumMember = AlbumMember.builder()
                    .user(user.getId())
                    .group(albumId)
                    .build();
            albumMemberJPARepository.save(albumMember);
        }
    }
}
