package com.example.team2_be.album.member;

import com.example.team2_be.album.Album;
import com.example.team2_be.album.AlbumJPARepository;
import com.example.team2_be.album.dto.AlbumMemberFindResponseDTO;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.example.team2_be.core.error.exception.UnauthorizedException;
import com.example.team2_be.user.User;
import com.example.team2_be.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlbumMemberService {

    private final UserJPARepository userJPARepository;
    private final AlbumJPARepository albumJPARepository;
    private final AlbumMemberJPARepository albumMemberJPARepository;

    public AlbumMemberFindResponseDTO getMembers(Long albumId) {
        List<AlbumMember> members = albumMemberJPARepository.findAllByAlbumId(albumId);
        List<User> users = members.stream()
                .map(member -> userJPARepository.getReferenceById(member.getUser().getId()))
                .collect(Collectors.toList());

        return new AlbumMemberFindResponseDTO(users);
    }

    @Transactional
    public void addMembers(Long userId, Long albumId) {
        User user = userJPARepository.getReferenceById(userId);
        // 앨범이 없을 경우 예외 처리, 후에 추가 수정
        Album album = albumJPARepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("해당 앨범이 존재하지 않습니다."));

        AlbumMember albumMember = albumMemberJPARepository.findByAlbumIdAndUserId(albumId, userId);
        if (albumMember == null) {
            albumMember = AlbumMember.builder()
                    .user(user)
                    .album(album)
                    .build();
            albumMemberJPARepository.save(albumMember);
        }
    }

    @Transactional
    public void deleteMembers(Long userId, Long albumId) {
        AlbumMember albumMember = albumMemberJPARepository.findByAlbumIdAndUserId(albumId, userId);

        albumMemberJPARepository.delete(albumMember);
    }

    // 해당 앨범 멤버 판별 기능
    public void checkMembership(Long userId, Long albumId){
        AlbumMember member = albumMemberJPARepository.findByAlbumIdAndUserId(albumId, userId);
        if(member == null){
            throw new UnauthorizedException("해당 앨범에 접근이 불가합니다.");
        }
    }
}
