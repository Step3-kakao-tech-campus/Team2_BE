package com.example.team2_be.trash;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.album.page.AlbumPage;
import com.example.team2_be.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Trash extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn (name ="albumPage_id",nullable = false)
    private AlbumPage albumPage;

    @Builder
    public Trash(Long id, User user, AlbumPage albumPage) {
        super(id);
        this.user = user;
        this.albumPage =albumPage;
    }
}

