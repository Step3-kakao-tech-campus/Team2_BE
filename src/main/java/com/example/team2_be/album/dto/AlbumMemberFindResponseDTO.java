package com.example.team2_be.album.dto;

import com.example.team2_be.user.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AlbumMemberFindResponseDTO {
    List<MemberDTO> members;

    public AlbumMemberFindResponseDTO(List<User> users){
        this.members = users.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }
}

@Getter
class MemberDTO{
    private Long id;
    private String nickname;
    private String image;

    public MemberDTO(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.image = user.getImage();
    }
}