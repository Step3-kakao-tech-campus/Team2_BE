package com.example.team2_be.album.member;

import com.example.team2_be.album.Album;
import com.example.team2_be.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "albumMember", indexes = {@Index(name = "idx_user_id", columnList = "user_id"), @Index(name = "idx_album_id", columnList = "album_id")})
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
    @JoinColumn(name = "album_id")
    private Album album;

    @Builder
    public AlbumMember(User user, Album album){
        this.user = user;
        this.album = album;
    }
}
