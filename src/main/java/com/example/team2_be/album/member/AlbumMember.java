package com.example.team2_be.album.member;

import com.example.team2_be.album.Album;
import com.example.team2_be.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "albumMember")
@Getter
@ToString
@NoArgsConstructor
public class AlbumMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Album group;

    @Builder
    public AlbumMember(User user, Album group){
        this.user = user;
        this.group = group;
    }
}
