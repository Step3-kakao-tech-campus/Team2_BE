package com.example.team2_be.album.member;

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

    @Column(nullable = false)
    private Long user;

    @Column(nullable = false)
    private Long group;

    @Builder
    public AlbumMember(Long user, Long group){
        this.user = user;
        this.group = group;
    }
}
