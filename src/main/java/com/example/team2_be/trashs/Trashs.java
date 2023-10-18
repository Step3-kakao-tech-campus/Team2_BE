package com.example.team2_be.trashs;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.album.page.AlbumPage;
import com.example.team2_be.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trashs")
@Getter
@ToString
@NoArgsConstructor
public class Trashs extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn (name ="albumPage_id",nullable = false)
    private AlbumPage albumPage;


    @Column (nullable = false)
    private LocalDateTime deleteAt;

    @Builder
    public Trashs(Long id, User user, AlbumPage albumPage) {
        super(id);
        this.user = user;
        this.albumPage =albumPage;
        // 앨범 페이지 삭제 시간
        this.deleteAt = this.getCreateAt().plusDays(7);
    }
}
